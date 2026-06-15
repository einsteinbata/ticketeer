package com.ticketeer.ticketeer_purchases_worker.repository;

import com.ticketeer.pojo.io.PurchaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseDto,Long> {
    PurchaseDto save(PurchaseDto purchaseDto);
    PurchaseDto findByPurchaseUuid(String purchaseUuid);
    List<PurchaseDto> findByEventId(Long eventId);
    PurchaseDto findByPaymentId(String paymentId);
}
