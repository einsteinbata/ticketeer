package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceOutput;
import com.ticketeer.pojo.model.Venue;
import lombok.Data;

@Data
public class AddVenueOutput extends ServiceOutput {

    public AddVenueOutput(){
        super();
    }

    public AddVenueOutput(Venue venue){
        super();
        this.venue = venue;
    }

    private Venue venue;
}
