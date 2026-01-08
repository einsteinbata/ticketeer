package com.ticketeer.pojo.constraints;

import lombok.Data;

@Data
public class VenueSearchConstraints {
    private Long id;
    private String city;
    private int capacity;
}
