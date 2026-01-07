package com.ticketeer.repository;

import com.ticketeer.pojo.model.Venue;

public interface VenueRepository {
    Venue getVenueById(Long venueId);
    Venue saveVenue(Venue venue);
}
