package com.ticketeer.repository.impl;

import com.ticketeer.pojo.model.Venue;
import com.ticketeer.repository.VenueRepository;
import org.springframework.stereotype.Repository;

@Repository
public class VenueRepositoryImpl implements VenueRepository {
    @Override
    public Venue getVenueById(Long venueId) {
        //TODO implement this
        return new Venue();
//        return null;
    }
    @Override
    public Venue saveVenue(Venue venue) {
        Venue savedVenue = venue;
        savedVenue.setVenueId(35L);
        return savedVenue;
    }
}
