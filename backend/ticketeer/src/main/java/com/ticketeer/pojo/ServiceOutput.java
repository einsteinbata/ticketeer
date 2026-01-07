package com.ticketeer.pojo;

import lombok.Data;

@Data
public class ServiceOutput {
    private int operationStatus = 0;
    private String errorMessage;
}
