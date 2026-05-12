package com.ticketeer.ticketeer_purchases_worker.service;

import com.ticketeer.ticketeer_purchases_worker.io.PurchaseMessageInput;
import org.springframework.stereotype.Service;

@Service
public class PurchaseConsumerServiceImpl implements PurchaseConsumerService {
    @Override
    public void processMessage(PurchaseMessageInput message) {
        System.out.println("Processing messsage: " + message);
        //TODO implent this
    }
}
