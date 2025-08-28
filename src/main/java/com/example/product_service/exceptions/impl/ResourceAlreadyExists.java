package com.example.product_service.exceptions.impl;

import com.example.product_service.exceptions.BaseException;

import java.util.HashMap;
import java.util.Map;

public class ResourceAlreadyExists extends BaseException {
    private final String resource;
    public ResourceAlreadyExists(String resource) {
        super("Resource already exists.", 409);
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
