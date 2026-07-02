package com.ticketeer.ticketeer_purchases_producer.service.payment;

import com.ticketeer.exceptions.ThirdPartyException;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PaymentOutput;
import com.ticketeer.pojo.io.PaymentReversalOutput;
import com.ticketeer.pojo.io.PaymentStatusOutput;

public interface PaymentService {
    PaymentOutput executePayment(PerformPurchaseInput purchaseInput) throws ThirdPartyException;
    PaymentStatusOutput checkPaymentStatus(String paymentId) throws ThirdPartyException;
    PaymentReversalOutput revertTransaction(String paymentId) throws ThirdPartyException;
}
