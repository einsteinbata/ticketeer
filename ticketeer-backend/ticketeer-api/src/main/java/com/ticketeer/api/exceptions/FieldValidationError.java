package com.ticketeer.api.exceptions;

public class FieldValidationError extends Exception {

    public FieldValidationError(){
    }

    public FieldValidationError(String message){
        super(message);
    }
}
