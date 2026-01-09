package com.ticketeer.service;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;

public interface VenueService {
    AddVenueOutput addVenue(AddVenueInput addVenueInput);
    GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) throws ServiceException;
}
