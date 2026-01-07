package com.ticketeer.pojo.constraints;

import com.ticketeer.pojo.model.City;
import lombok.Data;

@Data
public class VenueSearchConstraints {
    private City city;
    private int capacity;
}
