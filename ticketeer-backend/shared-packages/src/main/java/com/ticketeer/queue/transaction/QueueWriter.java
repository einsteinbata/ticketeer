package com.ticketeer.queue.transaction;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class QueueWriter {

    private final RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    public QueueWriter(RabbitTemplate rabbitTemplate, String exchange, String routingKey){
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void write(Object content) throws Exception {

        try{
            rabbitTemplate.convertAndSend(exchange, routingKey, content);
        } catch (AmqpException err) {
            System.err.println("Error sending message to queue. " + err.getMessage());
            throw new Exception(err);
        }

    }

}
