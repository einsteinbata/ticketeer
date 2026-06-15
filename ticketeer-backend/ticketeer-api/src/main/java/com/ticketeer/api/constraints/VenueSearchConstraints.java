package com.ticketeer.api.constraints;

import lombok.Data;

@Data
public class VenueSearchConstraints {
    private Long id;
    private String name;
    private String city;
}
