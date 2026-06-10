package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteVenueOutput extends ServiceOutput{
    private Long venueId;
}
