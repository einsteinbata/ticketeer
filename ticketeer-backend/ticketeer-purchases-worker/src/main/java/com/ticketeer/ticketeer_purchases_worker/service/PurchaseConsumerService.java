package com.ticketeer.ticketeer_purchases_worker.service;

import com.ticketeer.ticketeer_purchases_worker.io.PurchaseMessageInput;

public interface PurchaseConsumerService {
    void processMessage(PurchaseMessageInput message);
}
