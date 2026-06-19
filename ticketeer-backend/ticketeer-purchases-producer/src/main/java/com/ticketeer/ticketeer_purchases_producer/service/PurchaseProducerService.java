package com.ticketeer.ticketeer_purchases_producer.service;


import com.ticketeer.pojo.io.PerformPurchaseInput;

public interface PurchaseProducerService {

    void sendToRegularQueue(PerformPurchaseInput purchaseInput) throws Exception;

}
