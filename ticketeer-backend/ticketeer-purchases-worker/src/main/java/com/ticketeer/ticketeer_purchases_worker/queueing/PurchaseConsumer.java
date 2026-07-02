package com.ticketeer.ticketeer_purchases_worker.queueing;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PurchaseDto;
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
    public void consume(PerformPurchaseInput performPurchaseInput){
        //TODO consider implementing manual acknowledgement

        System.out.println("Message read from queue:");
        System.out.println(performPurchaseInput);

        PurchaseDto purchaseDto = null;

        try {
            purchaseDto = purchaseConsumerService.processMessage(performPurchaseInput);
            System.out.println("Successfully processed message: " + purchaseDto);
        } catch (Exception err) {
            System.err.println("Error processing message: [" + performPurchaseInput + "] " + err);
        }
    }

}
