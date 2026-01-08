package com.ticketeer.pojo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatCategory {
    private int numberOfSeats;
    private String category;
    private BigDecimal ticketPrice;
}
