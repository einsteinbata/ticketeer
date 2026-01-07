package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceInput;
import lombok.Data;

@Data
public class AddOrganizerInput extends ServiceInput {
    private String organizerName;
    private String organizerEmail;
    private String organizerPhoneNumber;
    private String organizerAddress;
}
