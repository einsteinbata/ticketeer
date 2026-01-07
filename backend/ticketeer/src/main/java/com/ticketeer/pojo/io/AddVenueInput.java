package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceInput;
import lombok.Data;

@Data
public class AddVenueInput extends ServiceInput {
    private String venueName;
    private String venueCity;
    private String venueCoordinates;
    private String managementEmail;
    private String managementPhone;
    private int capacity;
}
