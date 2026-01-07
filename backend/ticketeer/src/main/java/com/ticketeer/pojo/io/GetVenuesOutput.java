package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceOutput;
import com.ticketeer.pojo.model.Venue;
import lombok.Data;

import java.util.List;

@Data
public class GetVenuesOutput extends ServiceOutput {
    private List<Venue> venueList;
}
