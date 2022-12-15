package com.tech.turtles.todos.todoList.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.turtles.todos.todoList.entity.EntityPackageMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ObjectMapConverter {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectMapConverter.class);

    private ObjectMapper objectMapper;

    @Autowired
    public ObjectMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @NonNull
    public Map<String, Object> convertToMap(@NonNull Object object) {
        Map<String, Object> map = objectMapper.convertValue(object, Map.class);
        map.put("_class", object.getClass().getSimpleName());
        return map;
    }

    @Nullable
    public <T> T convertToObject(@NonNull Map<String, Object> map) {
        return convertToObject(map, null);
    }

    @Nullable
    public <T> T convertToObject(@NonNull Map<String, Object> map, @Nullable Package modelPackage) {
        T object = null;
        String modelClassFqName = null;
        try {
            String modelPackageName;
            if (modelPackage == null) {
                modelPackageName = EntityPackageMarker.class.getPackageName();
            } else {
                modelPackageName = modelPackage.getName();
            }
            String modelClassName = (String) map.get("_class");
            modelClassFqName = modelPackageName + "." + modelClassName;
            Class<?> modelClass = Class.forName(modelClassFqName);
            object = (T) objectMapper.convertValue(map, modelClass);
        } catch (ClassNotFoundException e) {
            LOG.error("Class not found: {}", modelClassFqName, e);
        } catch (ClassCastException e) {
            LOG.error("Class cannot be casted to {}", modelClassFqName, e);
        }
        return object;
    }

}
