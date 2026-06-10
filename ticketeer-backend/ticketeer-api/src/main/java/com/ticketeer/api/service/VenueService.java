package com.ticketeer.api.service;

import com.ticketeer.api.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.api.exceptions.ServiceException;
import com.ticketeer.api.pojo.io.AddVenueInput;
import com.ticketeer.api.pojo.io.AddVenueOutput;
import com.ticketeer.api.pojo.io.GetVenuesOutput;
import com.ticketeer.api.pojo.io.DeleteVenueOutput;

public interface VenueService {
    AddVenueOutput addVenue(AddVenueInput addVenueInput);
    GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) throws ServiceException;
    DeleteVenueOutput deleteVenue(Long venueId) throws ServiceException;
}
