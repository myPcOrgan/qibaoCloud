package com.qibao.common.utils;

import java.lang.reflect.Field;
import java.util.*;

public class ParamUtils {
    public static <T> Map<String, Object> convertMap(T obj) {
        Map<String, Object> params = new HashMap<String, Object>();
        Class<?> objClass = obj.getClass();
        List<Field> fieldList = new ArrayList<Field>();
        while (objClass != null) {
            fieldList.addAll(Arrays.asList(objClass.getDeclaredFields()));
            objClass = objClass.getSuperclass();
        }
        for (Field field : fieldList) {
            String name = field.getName();
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null && !value.toString().isEmpty()) {
                    params.put(name, value);
                }
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        return params;
    }
}
