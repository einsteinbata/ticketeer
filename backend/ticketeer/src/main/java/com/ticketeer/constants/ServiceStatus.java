package com.ticketeer.constants;

import lombok.Getter;

@Getter
public enum ServiceStatus {
    SUCCESS(0, "Success"),
    ERROR(2, "Error");

    int cod;
    String msg;

    ServiceStatus(int cod, String msg){
        this.cod = cod;
        this.msg = msg;
    }
}
