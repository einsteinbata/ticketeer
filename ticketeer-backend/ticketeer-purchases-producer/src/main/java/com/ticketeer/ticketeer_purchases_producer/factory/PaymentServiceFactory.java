package com.ticketeer.ticketeer_purchases_producer.factory;

import com.ticketeer.constants.PaymentMethod;
import com.ticketeer.exceptions.InvalidPaymentMethodException;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.ticketeer_purchases_producer.service.payment.PaymentService;
import com.ticketeer.ticketeer_purchases_producer.service.payment.impl.CashPaymentService;
import com.ticketeer.ticketeer_purchases_producer.service.payment.impl.MobileWalletPaymentService;
import com.ticketeer.ticketeer_purchases_producer.service.payment.impl.VisaPaymentService;

public class PaymentServiceFactory {

    public static PaymentService getService(PaymentMethod paymentMethod) throws InvalidPaymentMethodException {

        PaymentService paymentService = null;

        switch (paymentMethod) {

            case CASH -> paymentService = new CashPaymentService();
            case VISA -> paymentService = new VisaPaymentService();
            case MPESA, EMOLA -> paymentService = new MobileWalletPaymentService();
            default -> throw new InvalidPaymentMethodException("Payment method not available: " + paymentMethod.name());
        }

        return paymentService;
    }

}
