package com.ticketeer.pojo.io;

import com.ticketeer.pojo.dto.VenueDto;
import lombok.Data;

import java.util.List;

@Data
public class GetVenuesOutput extends ServiceOutput {
    private List<VenueDto> venueList;
}
