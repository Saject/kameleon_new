package com.example.kameleon.utils.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Collection;
import java.util.Map;

public class Utils {
    private Utils() {
        throw new RuntimeException();
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return oMapper;
    }

    public static boolean isEmpty(Object object) {
        if (isNull(object)) {
            return true;
        } else if (object instanceof Collection) {
            return isEmpty((Collection) object);
        } else if (object instanceof String) {
            return isEmpty((String) object);
        } else if (object instanceof Map) {
            return isEmpty((Map) object);
        } else if (object instanceof Map.Entry) {
            return isEmpty((Map.Entry) object);
        } else if (object instanceof JsonNode) {
            return isEmpty((JsonNode) object);
        } else if(object.getClass().isArray()) {
            return isEmpty((Object[]) object);
        }
        return false;
    }

    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    public static boolean isEmpty(JsonNode jsonNode) {
        return isNull(jsonNode) || jsonNode.isNull() || jsonNode.size() == 0 || jsonNode.isEmpty(null);
    }

    public static boolean isEmpty(String s) {
        return isNull(s) || s.isEmpty();
    }

    public static boolean isEmpty(Collection c) {
        return isNull(c) || c.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isEmpty(Map.Entry entry) {
        return isNull(entry) || isEmpty(entry.getValue());
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isNull(Object o) {
        return o == null;
    }
}
