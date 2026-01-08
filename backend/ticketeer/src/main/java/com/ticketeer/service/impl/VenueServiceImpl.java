package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.pojo.dto.VenueDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

            VenueDto venueDto = ObjectMapper.inputToDto(addVenueInput);

            VenueDto savedVenue = venueRepository.save(venueDto);

            System.out.println("Venue created " + savedVenue.toString());

            addVenueOutput.setVenue(savedVenue);

        } catch (FieldValidationError error) {
            System.err.println(error);
        }

        return addVenueOutput;
    }

    @Override
    public GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) {
        GetVenuesOutput getVenuesOutput = new GetVenuesOutput();

        System.out.println("Listing venues by constraints: " + getVenuesSearchConstraints);

        List<VenueDto> venuesList;

        if(Objects.isNull(getVenuesSearchConstraints)){
            venuesList = venueRepository.findAll();
            getVenuesOutput.setVenueList(venuesList);
            return getVenuesOutput;
        }

        if(Objects.nonNull(getVenuesSearchConstraints.getId())){
            VenueDto venue = null;

            Optional<VenueDto> venueDtoOptional = venueRepository.findById(getVenuesSearchConstraints.getId());

            if(venueDtoOptional.isPresent()){
                venue = venueDtoOptional.get();
                venuesList = new ArrayList<>();
                venuesList.add(venue);
                getVenuesOutput.setVenueList(venuesList);
                return getVenuesOutput;
            }

        }

        return new GetVenuesOutput();
    }
}
