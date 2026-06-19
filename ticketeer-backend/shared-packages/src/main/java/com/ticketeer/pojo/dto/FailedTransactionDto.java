package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "failed_transactions")
public class FailedTransactionDto {
    @Id
    @Column
    private String purchaseUuid;

    @Column
    private String paymentId;

    @Column
    private boolean paymentIsExecuted;

    @Column
    private int numberOfItems;

    @Column
    private String itemDescription;

    @Column
    private Long eventId;

    @Column
    private String paymentMethod;

    @Column
    private String failureDate;

    @Column
    private String paymentRequestDate;

}
