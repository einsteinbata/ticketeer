package com.ticketeer.ticketeer_purchases_producer.service.payment.impl;

import com.ticketeer.constants.Operation;
import com.ticketeer.constants.PaymentMethod;
import com.ticketeer.constants.ServiceStatus;
import com.ticketeer.exceptions.ThirdPartyException;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PaymentOutput;
import com.ticketeer.pojo.io.PaymentReversalOutput;
import com.ticketeer.pojo.io.PaymentStatusOutput;
import com.ticketeer.ticketeer_purchases_producer.service.payment.PaymentService;
import com.ticketeer.util.UuidUtil;

public class CashPaymentService implements PaymentService {
    @Override
    public PaymentOutput executePayment(PerformPurchaseInput purchaseInput) throws ThirdPartyException {
        //TODO implement this

        PaymentOutput output = new PaymentOutput();
        output.setPaymentId(UuidUtil.generateUUID(PaymentMethod.CASH.getPrefix()));
        output.setOperationStatus(ServiceStatus.SUCCESS.getCod());



        return output;
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
