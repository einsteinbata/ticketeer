package com.ticketeer.pojo.io;

import com.ticketeer.pojo.dto.VenueDto;
import lombok.Data;

@Data
public class AddVenueOutput extends ServiceOutput {

    public AddVenueOutput(){
        super();
    }

    public AddVenueOutput(VenueDto venue){
        super();
        this.venue = venue;
    }

    private VenueDto venue;
}
