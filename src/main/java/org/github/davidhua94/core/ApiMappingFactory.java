package org.github.davidhua94.core;

import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author David Hua
 * @date 2021/3/29
 * @desc
 */
@ApplicationScoped
public class ApiMappingFactory {

    private static final Logger logger = LoggerFactory.getLogger(ApiMappingFactory.class);

    private static Map<String, ApiHandlerMapping> apiMapping = new HashMap<>();

    private void registryApi() {
        Set<Bean<?>> beans = CDI.current().getBeanManager().getBeans(Object.class);

        beans.forEach(b -> {
            Bean<?> bean = b;
            Class<?> clazz = bean.getBeanClass();
            Api api = clazz.getAnnotation(Api.class);
            if (api != null) {
                if (apiMapping.containsKey(api.value())) {
                        logger.error("Api registry fail, {} already exist!", api.value());
                    } else {
                        ApiHandlerMapping apiHandlerMapping = new ApiHandlerMapping();
                        apiHandlerMapping.setApiClass((Class<LambdaApi<Request, Response>>)clazz);

                        Class<?>[] reqRespTypeFromApiClass = getReqRespTypeFromApiClass(clazz);
                        apiHandlerMapping.setRequestClass((Class<? extends Request>)reqRespTypeFromApiClass[0]);
                        apiHandlerMapping.setResponseClass((Class<? extends Response>)reqRespTypeFromApiClass[1]);
                        apiMapping.put(api.value(), apiHandlerMapping);

                        logger.info("Api registry success: name = {}, class = {}", api.value(), clazz.getName());
                    }
            }
        });
    }

    public ApiHandlerMapping getHandler(String action) {
        return apiMapping.get(action);
    }

    void onStart(@Observes StartupEvent ev) {
        logger.info("The application is starting...");
        registryApi();
    }

    private Class<?>[] getReqRespTypeFromApiClass(Class<?> apiRequestClass) {
        Class<?>[] result = new Class[2];
        Type[] type = apiRequestClass.getGenericInterfaces();
        Type interfaceType = type[0];

        Type[] actualTypeArguments = ((ParameterizedType) interfaceType).getActualTypeArguments();
        Class<? extends Request> requestType = (Class) actualTypeArguments[0];
        Class<? extends Response> responseType = (Class) actualTypeArguments[1];
        result[0] = requestType;
        result[1] = responseType;

        return result;
    }

    public static class ApiHandlerMapping {
        private Class<LambdaApi<Request, Response>> apiClass;

        private Class<? extends Request> requestClass;

        private Class<? extends Response> responseClass;

        public Class<LambdaApi<Request, Response>> getApiClass() {
            return apiClass;
        }

        public void setApiClass(Class<LambdaApi<Request, Response>> apiClass) {
            this.apiClass = apiClass;
        }

        public Class<? extends Request> getRequestClass() {
            return requestClass;
        }

        public void setRequestClass(Class<? extends Request> requestClass) {
            this.requestClass = requestClass;
        }

        public Class<? extends Response> getResponseClass() {
            return responseClass;
        }

        public void setResponseClass(Class<? extends Response> responseClass) {
            this.responseClass = responseClass;
        }
    }
}
