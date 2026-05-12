package com.ticketeer.ticketeer_purchases_worker.consumer;

import com.google.gson.Gson;
import com.ticketeer.ticketeer_purchases_worker.config.RabbitMqConfig;
import com.ticketeer.ticketeer_purchases_worker.io.PurchaseMessageInput;
import com.ticketeer.ticketeer_purchases_worker.service.PurchaseConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConsumer {

    @Autowired
    private PurchaseConsumerService purchaseConsumerService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void consume(String message){
        System.out.println("Message read from queue:");
        System.out.println(message);

        PurchaseMessageInput purchaseMessageInput =
                new Gson().fromJson(message, PurchaseMessageInput.class);

        purchaseConsumerService.processMessage(purchaseMessageInput);
    }

}
