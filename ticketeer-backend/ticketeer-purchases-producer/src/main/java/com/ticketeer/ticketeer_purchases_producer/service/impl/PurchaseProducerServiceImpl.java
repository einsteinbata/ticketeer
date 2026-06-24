package com.ticketeer.ticketeer_purchases_producer.service.impl;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.ticketeer_purchases_producer.service.PurchaseProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import org.springframework.amqp.AmqpException;

@Service
public class PurchaseProducerServiceImpl implements PurchaseProducerService {

    @Autowired
    private Environment env;
    private final RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    public PurchaseProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;

    }

    //TODO queue write logic should be in a shared package
    @Override
    public void sendToRegularQueue(PerformPurchaseInput purchaseInput) throws Exception {

        exchange = env.getProperty("rabbitmq.exchange");
        routingKey = env.getProperty("rabbitmq.routing.key");

        try {

            System.out.println("Queueing message with ID " + purchaseInput.getPurchaseUuid());

            rabbitTemplate.convertAndSend(
                    exchange,
                    routingKey,
                    purchaseInput
            );

        } catch (AmqpException err) {
            System.err.println("Error sending message to queue. " + err.getMessage());
            throw new Exception(err);
        }

    }

}
