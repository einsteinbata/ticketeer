package com.ticketeer.ticketeer_purchases_producer.service.payment.impl;

import com.ticketeer.exceptions.ThirdPartyException;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PaymentOutput;
import com.ticketeer.pojo.io.PaymentReversalOutput;
import com.ticketeer.pojo.io.PaymentStatusOutput;
import com.ticketeer.ticketeer_purchases_producer.service.payment.PaymentService;

public class MobileWalletPaymentService implements PaymentService {
    @Override
    public PaymentOutput executePayment(PerformPurchaseInput purchaseInput) throws ThirdPartyException {
        return null;
    }

    @Override
    public PaymentStatusOutput checkPaymentStatus(String paymentId) throws ThirdPartyException {
        return null;
    }

    @Override
    public PaymentReversalOutput revertTransaction(String paymentId) throws ThirdPartyException {
        return null;
    }
}
