package com.ticketeer.exceptions;

import lombok.NoArgsConstructor;

public class FieldValidationError extends Exception {

    public FieldValidationError(){
    }

    public FieldValidationError(String message){
        super(message);
    }
}
