package com.ticketeer.ticketeer_purchases_worker.service;


import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;

public interface PurchaseConsumerService {
    PurchaseDto processMessage(PurchaseMessageInput message) throws ServiceException;
}
