package com.ticketeer.ticketeer_purchases_producer.service;

import com.ticketeer.ticketeer_purchases_producer.io.PerformPurchaseInput;

public interface PurchaseProducerService {

    void send(PerformPurchaseInput purchaseInput) throws Exception;

}
