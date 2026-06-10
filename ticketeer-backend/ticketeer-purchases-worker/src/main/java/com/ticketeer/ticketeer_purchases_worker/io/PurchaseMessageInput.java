package com.ticketeer.ticketeer_purchases_worker.io;

import lombok.Data;

@Data
public class PurchaseMessageInput {
    long eventId;
    String itemDescription;
    int numberOfItems;
    String paymentId;
    String paymentMethod;
    String purchaseUuid;
}
