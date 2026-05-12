package com.ticketeer.ticketeer_purchases.service;

import com.ticketeer.ticketeer_purchases.io.PerformPurchaseInput;

public interface PurchaseProducerService {

    void send(PerformPurchaseInput purchaseInput) throws Exception;

}
