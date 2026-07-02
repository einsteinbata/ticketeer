package com.ticketeer.constants;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CASH("CASH_"),
    VISA("VISA_"),
    MASTERCARD("MCARD_"),
    MPESA("MPESA_"),
    EMOLA("EMOLA_");

    private String prefix;

    PaymentMethod(String prefix){
        this.prefix = prefix;
    }

}
