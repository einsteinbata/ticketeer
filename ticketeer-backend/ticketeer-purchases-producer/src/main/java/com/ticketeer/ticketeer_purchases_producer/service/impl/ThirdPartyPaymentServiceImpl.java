package com.ticketeer.ticketeer_purchases_producer.service.impl;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.ThirdPartyPaymentOutput;
import com.ticketeer.pojo.io.ThirdPartyPaymentReversalOutput;
import com.ticketeer.pojo.io.ThirdPartyPaymentStatusOutput;
import com.ticketeer.ticketeer_purchases_producer.service.ThirdPartyPaymentService;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPaymentServiceImpl implements ThirdPartyPaymentService {
    @Override
    public ThirdPartyPaymentOutput executePayment(PerformPurchaseInput purchaseInput) {
        return null;
    }

    @Override
    public ThirdPartyPaymentStatusOutput checkPaymentStatus(String paymentId) {
        return null;
    }

    @Override
    public ThirdPartyPaymentReversalOutput revertTransaction(String paymentId) {
        return null;
    }
}
