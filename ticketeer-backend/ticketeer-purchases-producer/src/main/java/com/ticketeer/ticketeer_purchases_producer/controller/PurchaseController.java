package com.ticketeer.ticketeer_purchases_producer.controller;

import com.ticketeer.constants.Operation;
import com.ticketeer.exceptions.ThirdPartyException;
import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.ThirdPartyPaymentReversalOutput;
import com.ticketeer.ticketeer_purchases_producer.service.PurchaseProducerService;
import com.ticketeer.ticketeer_purchases_producer.service.ThirdPartyPaymentService;
import com.ticketeer.util.DateUtil;
import com.ticketeer.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseProducerService purchaseProducerService;

    @Autowired
    private ThirdPartyPaymentService thirdPartyPaymentService;

    public ResponseEntity listPurchases(){
        return null;
    }

    @PostMapping("/execute")
    public ResponseEntity<PerformPurchaseInput> perform(@RequestBody PerformPurchaseInput purchaseInput){

        purchaseInput.setPurchaseUuid(UuidUtil.generateUUID(Operation.PURCHASE));
        purchaseInput.setPaymentRequestDate(DateUtil.getFormattedDate());

        System.out.println("Purchase execution: " + purchaseInput);

        //Executing payment on third party
        try {
            thirdPartyPaymentService.executePayment(purchaseInput);
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

    private ThirdPartyPaymentReversalOutput performTransactionReversal(PerformPurchaseInput purchaseInput){
        ThirdPartyPaymentReversalOutput reversalOutput = null;

        try{
            reversalOutput = thirdPartyPaymentService.revertTransaction(purchaseInput.getPaymentId());
        } catch (ThirdPartyException err) {
            System.err.println("Could not revert transaction at third party [" + purchaseInput + " ] " + err);
        }

        return reversalOutput;
    }

}
