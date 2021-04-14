package org.github.davidhua94.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author David Hua
 * @date 2021/3/26
 * @desc
 */
public class JacksonHelper {
    private JacksonHelper() {}


    public static String serializable(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JacksonHelper.serializable() fail, error: ", e);
        }
    }

    public static <T> T deserialization(String jsonStr, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JacksonHelper.deserialization() fail, error: ", e);
        }
    }
}
