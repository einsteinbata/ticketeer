package com.ticketeer.ticketeer_purchases.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMqConfig {

    @Autowired
    private Environment env;

    @Bean
    public Queue queue(){
        String QUEUE = env.getProperty("rabbitmq.queue");
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public DirectExchange exchange(){
        String EXCHANGE = env.getProperty("rabbitmq.exchange");
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){

        String ROUTING_KEY = env.getProperty("rabbitmq.routing.key");

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

}
