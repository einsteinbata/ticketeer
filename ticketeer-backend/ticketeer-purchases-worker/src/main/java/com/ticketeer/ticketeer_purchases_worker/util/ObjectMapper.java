package com.ticketeer.ticketeer_purchases_worker.util;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.util.DateUtil;

public class ObjectMapper {

//    public static PurchaseDto purchaseMessageInputToPurchaseDto(PurchaseMessageInput purchaseMessageInput){
//        PurchaseDto purchaseDto = new PurchaseDto();
//
//        purchaseDto.setPurchaseUuid(purchaseMessageInput.getPurchaseUuid());
//        purchaseDto.setEventId(purchaseMessageInput.getEventId());
//        purchaseDto.setItemDescription(purchaseMessageInput.getItemDescription());
//        purchaseDto.setItemTotal(purchaseMessageInput.getNumberOfItems());
//        purchaseDto.setPaymentId(purchaseMessageInput.getPaymentId());
//        purchaseDto.setPaymentMethod(purchaseMessageInput.getPaymentMethod());
//        purchaseDto.setPaymentRequestDate(purchaseMessageInput.getPaymentRequestDate());
//        purchaseDto.setPurchaseProcessingDate(DateUtil.getFormattedDate());
//
//        return purchaseDto;
//    }

    public static PurchaseDto performPurchaseInputToPurchaseDto(PerformPurchaseInput performPurchaseInput){

        PurchaseDto purchaseDto = new PurchaseDto();

        purchaseDto.setPurchaseUuid(performPurchaseInput.getPurchaseUuid());
        purchaseDto.setEventId(performPurchaseInput.getEventId());
        purchaseDto.setItemDescription(performPurchaseInput.getItemDescription());
        purchaseDto.setItemTotal(performPurchaseInput.getNumberOfItems());
        purchaseDto.setPaymentId(performPurchaseInput.getPaymentId());
        purchaseDto.setPaymentMethod(performPurchaseInput.getPaymentMethod());
        purchaseDto.setPaymentRequestDate(performPurchaseInput.getPaymentRequestDate());

        return purchaseDto;

    }

}
