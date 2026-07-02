package com.ticketeer.ticketeer_purchases_producer.controller;

import com.ticketeer.constants.Operation;
import com.ticketeer.constants.PaymentMethod;
import com.ticketeer.constants.ServiceStatus;
import com.ticketeer.exceptions.InvalidPaymentMethodException;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.exceptions.ThirdPartyException;
import com.ticketeer.pojo.io.PaymentOutput;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PaymentReversalOutput;
import com.ticketeer.ticketeer_purchases_producer.factory.PaymentServiceFactory;
import com.ticketeer.ticketeer_purchases_producer.service.PurchaseProducerService;
import com.ticketeer.ticketeer_purchases_producer.service.payment.PaymentService;
import com.ticketeer.util.DateUtil;
import com.ticketeer.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseProducerService purchaseProducerService;

    private PaymentService paymentService;

    public ResponseEntity listPurchases(){
        return null;
    }

    @PostMapping("/execute")
    public ResponseEntity<PerformPurchaseInput> perform(@RequestBody PerformPurchaseInput purchaseInput) {

        purchaseInput.setPurchaseUuid(UuidUtil.generateUUID(Operation.PURCHASE.getPrefix()));
        purchaseInput.setPaymentRequestDate(DateUtil.getFormattedDate());

        System.out.println("Purchase execution: " + purchaseInput);

        PaymentOutput paymentOutput = null;

        //Executing local or third party payment
        try {

            paymentService = PaymentServiceFactory.getService(PaymentMethod.valueOf(purchaseInput.getPaymentMethod()));

            System.out.println("Executing payment using entity: " + paymentService.getClass().getSimpleName());
            paymentOutput = paymentService.executePayment(purchaseInput);

            if (paymentOutput.getOperationStatus() != ServiceStatus.SUCCESS.getCod())
                throw new ServiceException("Error performing payment on third party. "
                        + paymentOutput.getOperationStatus() + " - "
                        + paymentOutput.getErrorMessage()
                );

            purchaseInput.setPaymentId(paymentOutput.getPaymentId());

            System.out.println("Payment successful: " + purchaseInput);

        } catch (InvalidPaymentMethodException | IllegalArgumentException err){
            System.err.println("Invalid payment method: " + purchaseInput.getPaymentMethod());

            return ResponseEntity.badRequest().build();
        } catch (Exception err) {
            System.err.println("Could not execute payment on third party [" + purchaseInput + "]" + err);
            return ResponseEntity.internalServerError().body(purchaseInput);
        }

        //Registering purchase on the queue
        try {
            purchaseProducerService.sendToRegularQueue(purchaseInput);
        } catch (Exception err) {
            System.err.println("Could not send purchase message to queue");

            performTransactionReversal(purchaseInput);

            return ResponseEntity.internalServerError().body(purchaseInput);
        }

        return ResponseEntity.accepted().body(purchaseInput);
    }

    private PaymentReversalOutput performTransactionReversal(PerformPurchaseInput purchaseInput){
        PaymentReversalOutput reversalOutput = null;

        try{
            reversalOutput = paymentService.revertTransaction(purchaseInput.getPaymentId());
        } catch (ThirdPartyException err) {
            System.err.println("Could not revert transaction at third party [" + purchaseInput + " ] " + err);
        }

        return reversalOutput;
    }

}
