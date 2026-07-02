package com.ticketeer.ticketeer_purchases_worker.service;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.ticketeer_purchases_worker.repository.PurchaseRepository;
import com.ticketeer.ticketeer_purchases_worker.util.ObjectMapper;
import com.ticketeer.util.DateUtil;
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
    private String retryLimit;

    public PurchaseConsumerServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public PurchaseDto processMessage(PerformPurchaseInput performPurchaseInput) throws ServiceException {
        System.out.println("Processing messsage: " + performPurchaseInput);

        PurchaseDto purchaseDto = ObjectMapper.performPurchaseInputToPurchaseDto(performPurchaseInput);
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

        retryLimit = env.getProperty("queue.message.retry.limit");

        for(int attemptCount = 1; attemptCount<=Integer.parseInt(retryLimit); attemptCount++){

            try {
                //Message processing logic
                registeredPurchase = purchaseDto;
                registeredPurchase.setPurchaseProcessingDate(DateUtil.getFormattedDate());

                registeredPurchase = purchaseRepository.save(registeredPurchase);
                return registeredPurchase;
            } catch (Exception err) {
                System.err.println("Could not save purchase information at attempt [" + attemptCount + "]. "
                        + "[" + purchaseDto + "] ");
                System.err.println(err);
            }

        }

        throw new ServiceException("Message processing attempts limit reached.");
    }

    private void sendToDeadLetterQueue(PurchaseDto purchaseDto) throws ServiceException {

        dlqExchange = env.getProperty("rabbitmq.dlq.exchange");
        dlqRoutingKey = env.getProperty("rabbitmq.dlq.routing.key");

        try{
            System.out.println("Writing to queue [exchange=" + dlqExchange
                    + ", routing_key=" + dlqRoutingKey + "] Content: " + purchaseDto.toString());

            rabbitTemplate.convertAndSend(
                    dlqExchange,
                    dlqRoutingKey,
                    purchaseDto
            );

            System.out.println("Message successfully written to the DLQ. Key: " + purchaseDto.getPurchaseUuid());

        } catch (Exception err) {
            System.err.println("Error writing to DLQ. " + err);
            throw new ServiceException("Could not write to dead letter queue. " + err);
        }

    }

}
