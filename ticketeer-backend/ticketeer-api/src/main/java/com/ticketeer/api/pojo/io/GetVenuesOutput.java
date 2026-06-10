package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.dto.VenueDto;
import lombok.Data;

import java.util.List;

@Data
public class GetVenuesOutput extends ServiceOutput {
    private List<VenueDto> venueList;
}
