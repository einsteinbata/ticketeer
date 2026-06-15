package com.ticketeer.exceptions;

public class ResourceNotFoundError extends Exception {

    public ResourceNotFoundError(){

    }

    public ResourceNotFoundError(String message){
        super(message);
    }

}
