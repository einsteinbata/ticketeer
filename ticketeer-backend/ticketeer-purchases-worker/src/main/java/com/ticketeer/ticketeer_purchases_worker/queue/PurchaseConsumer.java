package com.ticketeer.ticketeer_purchases_worker.queue;

import com.google.gson.Gson;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.ticketeer_purchases_worker.config.RabbitMqConfig;
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

        PurchaseDto purchaseDto = null;

        try {
            purchaseDto = purchaseConsumerService.processMessage(purchaseMessageInput);
            System.out.println("Successfully processed message: " + purchaseDto);
        } catch (Exception err) {
            System.err.println("Error processing message: [" + purchaseMessageInput + "] " + err);
        }
    }

}
