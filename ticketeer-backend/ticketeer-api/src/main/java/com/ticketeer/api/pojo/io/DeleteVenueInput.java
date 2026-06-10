package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteVenueInput extends ServiceInput{
    private Long venueId;
}
