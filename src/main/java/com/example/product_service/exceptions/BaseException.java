package com.example.product_service.exceptions;

import lombok.Getter;

import java.util.Map;

public abstract class BaseException extends RuntimeException{
    protected final String message;
    @Getter
    private final int code;

    public BaseException(String message, int code){
        this.message = message;
        this.code = code;
    }

    public abstract Map<String, String> getExceptionAsMap();

}
