package com.itstan.exception;

import com.itstan.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e) {
        e.printStackTrace();
        return Result.error("Error! Please contact administrator");
    }
}
