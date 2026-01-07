package com.ticketeer.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SeatArrangement {
    private List<SeatCategory> seatCategoryList;
}
