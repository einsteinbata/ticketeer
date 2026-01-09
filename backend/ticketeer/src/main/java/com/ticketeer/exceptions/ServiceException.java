package com.ticketeer.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends Exception {

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable throwable){
        super(throwable);
    }

}
