package com.mm.common.utils;

import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;


public class JsonUtils {


    private static ObjectMapper objectMapper;
    private JsonUtils() {
    }
    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T json2Object(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            T object = objectMapper.readValue(json, clazz);
            return object;
        } catch (IOException e) {
            LOGGER.warn("json = " + json, e);
            return null;
        }
    }






    public static String object2Json(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.warn("Exception occured:", e);
            return "";
        }
    }




    /**
     * 获取泛型的Collection Type   如 List<User> list = readJson(json, List.class, User.class);
     *
     * @param json            json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T json2List(String json, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.warn("json = " + json, e);
            return null;
        }
    }


    /**
     *
     * @param json
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @param <T>
     * @return
     */
    public static <T> T json2Map(String json, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
        try {
            return objectMapper.readValue(json, javaType);
        }  catch (Exception e) {
            LOGGER.warn("json = " + json, e);
            return null;
        }

    }

}
