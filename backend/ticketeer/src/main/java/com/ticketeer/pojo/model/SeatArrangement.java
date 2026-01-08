package com.ticketeer.pojo.model;

import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

@Data
public class SeatArrangement {
    private List<SeatCategory> seatCategoryList;

    public String toJson(){
        return new Gson().toJson(this);
    }
}
