package com.ticketeer.ticketeer_purchases_producer.io;

import lombok.Data;

@Data
public class PerformPurchaseInput {
    private String purchaseUuid;
    private int numberOfItems;
    private String itemDescription;
    private String paymentId;
    private Long eventId;
    private String paymentMethod;
    private String paymentRequestDate;
}
