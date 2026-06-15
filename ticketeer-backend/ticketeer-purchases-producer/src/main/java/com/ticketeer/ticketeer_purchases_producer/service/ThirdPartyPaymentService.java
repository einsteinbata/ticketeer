package com.ticketeer.ticketeer_purchases_producer.service;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.ThirdPartyPaymentOutput;
import com.ticketeer.pojo.io.ThirdPartyPaymentReversalOutput;
import com.ticketeer.pojo.io.ThirdPartyPaymentStatusOutput;

public interface ThirdPartyPaymentService {
    ThirdPartyPaymentOutput executePayment(PerformPurchaseInput purchaseInput);
    ThirdPartyPaymentStatusOutput checkPaymentStatus(String paymentId);
    ThirdPartyPaymentReversalOutput revertTransaction(String paymentId);
}
