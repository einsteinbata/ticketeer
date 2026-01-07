package com.ticketeer.pojo.model;

import lombok.Data;

@Data
public class Organizer {
    private Long organizerId;
    private String organizerName;
    private String organizerEmail;
    private String organizerPhoneNumber;
    private String organizerAddress;
}
