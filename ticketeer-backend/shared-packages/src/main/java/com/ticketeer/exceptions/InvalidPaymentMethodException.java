package com.ticketeer.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidPaymentMethodException extends Exception {

    public InvalidPaymentMethodException(String message){
        super(message);
    }

    public InvalidPaymentMethodException(Throwable throwable){
        super(throwable);
    }

}
