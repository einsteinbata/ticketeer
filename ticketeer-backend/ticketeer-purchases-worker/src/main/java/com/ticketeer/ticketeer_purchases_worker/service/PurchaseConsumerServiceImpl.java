package com.ticketeer.ticketeer_purchases_worker.service;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.ticketeer_purchases_worker.repository.PurchaseRepository;
import com.ticketeer.ticketeer_purchases_worker.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseConsumerServiceImpl implements PurchaseConsumerService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public PurchaseDto processMessage(PurchaseMessageInput message) throws ServiceException {
        System.out.println("Processing messsage: " + message);

        PurchaseDto purchaseDto = ObjectMapper.purchaseMessageInputToPurchaseDto(message);

        PurchaseDto registeredPurchase = null;

        try {
            registeredPurchase = purchaseRepository.save(purchaseDto);
        } catch (Exception err) {
            System.err.println("Could not save purchase information. [" + purchaseDto + "] " + purchaseDto);
            throw new ServiceException(err);
        }

        return registeredPurchase;
    }
}
