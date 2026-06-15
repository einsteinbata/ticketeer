package com.ticketeer.api.controller.advice;

import com.ticketeer.pojo.io.GenericErrorOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorOutput> handleAll(Exception err){
        return ResponseEntity.internalServerError().body(new GenericErrorOutput(err));
    }

}
