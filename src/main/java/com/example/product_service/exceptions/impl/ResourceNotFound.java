package com.example.product_service.exceptions.impl;

import com.example.product_service.exceptions.BaseException;

import java.util.HashMap;
import java.util.Map;

public class ResourceNotFound extends BaseException {
    private final String resource;

    public ResourceNotFound(String resource) {
        super("Resource not found.", 404);
        this.resource = resource;
    }

    @Override
    public Map<String, String> getExceptionAsMap() {
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("resource", resource);
        return map;
    }
}
