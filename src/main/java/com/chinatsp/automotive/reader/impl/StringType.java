package com.chinatsp.automotive.reader.impl;

import com.chinatsp.automotive.reader.api.IClassType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Component
public class StringType implements IClassType {
    @SneakyThrows
    @Override
    public void setValue(Object object, Field field, Class<?> clazz, String className, String cellValue, int index) {
        log.trace("handle string type");
        String fieldName = field.getName();
        if (fieldName.equalsIgnoreCase("name") || fieldName.equalsIgnoreCase("ImageName")) {
            cellValue = cellValue.trim().replace(" ", "_")
                    .replace("__", "_")
                    .replace("-", "_");
        }
        if (fieldName.contains("Before") || fieldName.contains("After")) {
            cellValue = cellValue.replace("\n", " ");
        }
        field.set(object, cellValue);
    }

}
