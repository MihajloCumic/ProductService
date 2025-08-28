package com.example.product_service.exceptions;

import com.example.product_service.exceptions.impl.ResourceAlreadyExists;
import com.example.product_service.exceptions.impl.ResourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodValidationExceptions(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body("Argument not valid.");
    }
    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<Map<String, String>> resourceAlreadyExists(ResourceAlreadyExists ex){
        return ResponseEntity.status(ex.getCode()).body(ex.getExceptionAsMap());
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Map<String, String>> resourceNotFound(ResourceNotFound ex){
        return ResponseEntity.status(ex.getCode()).body(ex.getExceptionAsMap());
    }

}
