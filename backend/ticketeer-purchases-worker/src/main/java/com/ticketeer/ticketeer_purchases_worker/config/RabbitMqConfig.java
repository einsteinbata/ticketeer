package com.ticketeer.ticketeer_purchases_worker.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String QUEUE = "purchases";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }

}
