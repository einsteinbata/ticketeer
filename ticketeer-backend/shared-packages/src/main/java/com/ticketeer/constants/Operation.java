package com.ticketeer.constants;

public enum Operation {
    PURCHASE("PRCHS_");
    private String prefix;

    Operation(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
