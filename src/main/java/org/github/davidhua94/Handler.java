package org.github.davidhua94;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.quarkus.funqy.Funq;
import org.github.davidhua94.core.ApiMappingFactory;
import org.github.davidhua94.core.LambdaApi;
import org.github.davidhua94.core.Request;
import org.github.davidhua94.core.Response;
import org.github.davidhua94.exception.ApiException;
import org.github.davidhua94.util.JacksonHelper;
import org.github.davidhua94.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
public class Handler {
    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    @Inject
    ApiMappingFactory apiMappingFactory;

    @Funq
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event) {
        String action = event.getQueryStringParameters().get("action");

        if (!StringUtil.notBlank(action)) {
            throw new ApiException("Action not found!");
        }

        ApiMappingFactory.ApiHandlerMapping apiHandlerMapping = apiMappingFactory.getHandler(action);
        if (apiHandlerMapping == null) {
            throw new ApiException("Action not found!");
        }

        LambdaApi lambdaApi = CDI.current().select(apiHandlerMapping.getApiClass()).get();

        Request request;
        Response response;
        try {
            request = JacksonHelper.deserialization(event.getBody(), apiHandlerMapping.getRequestClass());

            Class<? extends Response> respClazz = apiHandlerMapping.getResponseClass();
            response = respClazz.newInstance();

        } catch (IllegalAccessException | InstantiationException e) {
            logger.error("Invoke lambda fail, ", e);
            return this.fail("Internal error!");
        }

        try {
            lambdaApi.execute(request, response);
        } catch (ApiException e) {
            return this.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("Internal server error: ", e);
            return this.fail("Internal Server Error!");
        }

        return this.success(response);
    }

    private APIGatewayProxyResponseEvent fail(String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", Boolean.FALSE);
        map.put("errorMessage", errorMessage);
        return new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(JacksonHelper.serializable(map));
    }

    private <T> APIGatewayProxyResponseEvent success(T data) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", Boolean.TRUE);
        map.put("data", data);
        return new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(JacksonHelper.serializable(map));
    }
}
