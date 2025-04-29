package com.example.labSystem.utils;

import java.lang.reflect.Field;

public class EnumReflectionUtil {
    public static void setEnumField(Enum<?> enumConstant, String fieldName, Object newValue) throws Exception {
        Field field = enumConstant.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(enumConstant, newValue);
    }
}

