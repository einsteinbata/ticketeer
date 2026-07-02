package com.ticketeer.ticketeer_purchases_producer.service;

import com.ticketeer.pojo.dto.FailedTransactionDto;
import com.ticketeer.pojo.io.PerformPurchaseInput;

public interface FailedTransactionService {
    FailedTransactionDto registerFailedTransaction(PerformPurchaseInput purchaseInput);
}
