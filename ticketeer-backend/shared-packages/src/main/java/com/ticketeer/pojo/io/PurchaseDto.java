package com.ticketeer.pojo.io;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "purchase")
public class PurchaseDto {

    @Id
    @Column(nullable = false, unique = true)
    String purchaseUuid;

    @Column(nullable = false)
    long eventId;

    @Column
    String itemDescription;

    @Column
    int numberOfItems;
    @Column(nullable = false, unique = true)
    String paymentId;

    @Column
    String paymentMethod;

    @Column
    String paymentRequestDate;

    @Column
    String purchaseProcessingDate;

}