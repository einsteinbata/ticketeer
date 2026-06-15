package com.ticketeer.ticketeer_purchases_worker.repository;

import com.ticketeer.ticketeer_purchases_worker.io.PurchaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepositoryImpl extends JpaRepository<PurchaseDto,Long> {
    PurchaseDto findByPurchaseId(Long purchaseId);
    PurchaseDto findByPurchaseUuid(String purchaseUuid);
    List<PurchaseDto> findByEventId(Long eventId);
    PurchaseDto findByPaymentId(String paymentId);
}
