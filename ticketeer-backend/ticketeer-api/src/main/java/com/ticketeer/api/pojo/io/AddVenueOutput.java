package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.dto.VenueDto;
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
