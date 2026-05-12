package com.ticketeer.ticketeer_purchases_producer.controller;

import com.ticketeer.ticketeer_purchases_producer.service.PurchaseProducerService;
import com.ticketeer.ticketeer_purchases_producer.io.PerformPurchaseInput;
import com.ticketeer.ticketeer_purchases_producer.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseProducerService purchaseProducerService;

    public ResponseEntity listPurchases(){
        return null;
    }

    @PostMapping("/execute")
    public ResponseEntity<PerformPurchaseInput> perform(@RequestBody PerformPurchaseInput purchaseInput){

        purchaseInput.setPurchaseUuid(MessageUtil.generateUUID());

        System.out.println("Purchase execution: " + purchaseInput);

        try {
            purchaseProducerService.send(purchaseInput);
        } catch (Exception err) {
            System.err.println("Could not send purchase message to queue");
            return ResponseEntity.internalServerError().body(purchaseInput);
        }

        return ResponseEntity.ok().body(purchaseInput);
    }

}
