package org.github.davidhua94;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.quarkus.amazon.lambda.test.LambdaClient;
import io.quarkus.test.junit.QuarkusTest;
import org.github.davidhua94.util.JacksonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Hua
 * @date 2021/4/13
 * @desc
 */
@QuarkusTest
public class HandlerTest {


    @Test
    public void testAddPet() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "dog");
        params.put("age", 30);
        APIGatewayProxyRequestEvent input = buildRequest("AddPet", params);
        APIGatewayProxyResponseEvent output = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, input);
        Assertions.assertEquals(200, output.getStatusCode());

        System.out.println(output.getBody());
    }

    @Test
    public void testGetPet() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("id", "36ef35a8370e4409a7ed5e905c7a1c9");
        APIGatewayProxyRequestEvent input = buildRequest("GetPet", params);
        APIGatewayProxyResponseEvent output = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, input);
        Assertions.assertEquals(200, output.getStatusCode());

        System.out.println(output.getBody());
    }

    @Test
    public void testGetFirmwareInfo() {
        Map<String, Object> params = new HashMap<>();
        params.put("firmwareId", "947231230d4f64204b7aee58a894c8b1");
        APIGatewayProxyRequestEvent input = buildRequest("GetFirmwareInfo", params);
        APIGatewayProxyResponseEvent output = LambdaClient.invoke(APIGatewayProxyResponseEvent.class, input);
        Assertions.assertEquals(200, output.getStatusCode());

        System.out.println(output.getBody());
    }


    private APIGatewayProxyRequestEvent buildRequest(String action, Map<String,Object> params) {
        APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", action);
        apiGatewayProxyRequestEvent.setQueryStringParameters(queryParams);
        apiGatewayProxyRequestEvent.setBody(JacksonHelper.serializable(params));

        return apiGatewayProxyRequestEvent;
    }
}
