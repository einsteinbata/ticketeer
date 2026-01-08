package com.ticketeer.pojo.io;

import lombok.Data;

@Data
public abstract class ServiceOutput {
    private int operationStatus = 0;
    private String errorMessage;
}
