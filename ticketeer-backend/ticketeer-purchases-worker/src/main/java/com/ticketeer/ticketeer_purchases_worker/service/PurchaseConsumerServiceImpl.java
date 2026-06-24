package com.ticketeer.ticketeer_purchases_worker.service;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.queue.transaction.QueueWriter;
import com.ticketeer.ticketeer_purchases_worker.repository.PurchaseRepository;
import com.ticketeer.ticketeer_purchases_worker.util.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PurchaseConsumerServiceImpl implements PurchaseConsumerService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private Environment env;
    private final RabbitTemplate rabbitTemplate;
    private String dlqExchange;
    private String dlqRoutingKey;
    private final String RETRY_LIMIT = env.getProperty("queue.message.retry.limit");

    public PurchaseConsumerServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public PurchaseDto processMessage(PurchaseMessageInput message) throws ServiceException {
        System.out.println("Processing messsage: " + message);

        PurchaseDto purchaseDto = ObjectMapper.purchaseMessageInputToPurchaseDto(message);
        PurchaseDto registeredPurchase = null;

        try{
            registeredPurchase = processWithRetry(purchaseDto);
            System.out.println("Message processed successfully [" + registeredPurchase + "]");
        } catch (ServiceException err){
            System.err.println("Could not process message [" + purchaseDto + "]. " + err);
            sendToDeadLetterQueue(purchaseDto);
            throw err;
        }

        return registeredPurchase;
    }

    private PurchaseDto processWithRetry(PurchaseDto purchaseDto) throws ServiceException {

        PurchaseDto registeredPurchase = null;

        for(int retryCount=0; retryCount<Integer.parseInt(RETRY_LIMIT); retryCount++){

            try {
                //Message processing logic
                registeredPurchase = purchaseRepository.save(purchaseDto);
                return registeredPurchase;
            } catch (Exception err) {
                System.err.println("Could not save purchase information at attempt [" + retryCount + "]. "
                        + "[" + purchaseDto + "] ");
            }

        }

        throw new ServiceException("Message processing attempts limit reached.");
    }

    private void sendToDeadLetterQueue(PurchaseDto purchaseDto) throws ServiceException {

        dlqExchange = env.getProperty("rabbitmq.dlq.exchange");
        dlqRoutingKey = env.getProperty("rabbitmq.dlq.routing.key");

        QueueWriter queueWriter = new QueueWriter(rabbitTemplate, dlqExchange, dlqRoutingKey);

        try{
            System.out.println("Writing to queue [exchange=" + dlqExchange + ", routing_key=" + dlqRoutingKey + "]");
            queueWriter.write(purchaseDto);
            System.out.println("Message successfully written to the queue");
        } catch (Exception err) {
            System.err.println("Error writing to dead letter queue. " + err);
            throw new ServiceException("Could not write to dead letter queue. " + err);
        }

    }

}
