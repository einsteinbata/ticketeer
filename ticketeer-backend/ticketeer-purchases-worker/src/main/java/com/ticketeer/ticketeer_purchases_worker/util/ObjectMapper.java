package com.ticketeer.ticketeer_purchases_worker.util;

import com.ticketeer.pojo.io.PerformPurchaseInput;
import com.ticketeer.pojo.io.PurchaseDto;
import com.ticketeer.pojo.io.PurchaseMessageInput;
import com.ticketeer.util.DateUtil;

public class ObjectMapper {

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
