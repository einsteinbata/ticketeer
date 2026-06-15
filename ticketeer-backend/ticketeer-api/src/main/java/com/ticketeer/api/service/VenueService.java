package com.ticketeer.api.service;

import com.ticketeer.api.constraints.VenueSearchConstraints;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.DeleteVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;


public interface VenueService {
    AddVenueOutput addVenue(AddVenueInput addVenueInput);
    GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) throws ServiceException;
    DeleteVenueOutput deleteVenue(Long venueId) throws ServiceException;
}
