package com.ticketeer.ticketeer_purchases_producer.service;


import com.ticketeer.pojo.io.PerformPurchaseInput;

public interface PurchaseProducerService {

    void send(PerformPurchaseInput purchaseInput) throws Exception;

}
