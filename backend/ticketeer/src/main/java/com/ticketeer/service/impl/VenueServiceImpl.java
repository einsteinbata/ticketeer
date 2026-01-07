package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.pojo.model.Venue;
import com.ticketeer.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;
import com.ticketeer.repository.VenueRepository;
import com.ticketeer.service.VenueService;
import com.ticketeer.util.ObjectMapper;
import com.ticketeer.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {

    private VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }

    @Override
    public AddVenueOutput addVenue(AddVenueInput addVenueInput) {

        AddVenueOutput addVenueOutput = new AddVenueOutput();

        System.out.println("Adding venue: " + addVenueInput.toString());

        try{
            ValidationUtil.validateAddVenueInput(addVenueInput);

            Venue venue = ObjectMapper.inputToModel(addVenueInput);

            Venue savedVenue = venueRepository.saveVenue(venue);

            System.out.println("Venue created " + savedVenue.toString());

            addVenueOutput.setVenue(savedVenue);

        } catch (FieldValidationError error) {
            System.err.println(error);
        }

        return addVenueOutput;
    }

    @Override
    public GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) {

        System.out.println("Listing venues by constraints: " + getVenuesSearchConstraints);

        return new GetVenuesOutput();
    }
}
