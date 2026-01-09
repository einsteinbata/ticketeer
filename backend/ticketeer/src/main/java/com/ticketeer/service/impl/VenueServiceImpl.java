package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.dto.VenueDto;
import com.ticketeer.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;
import com.ticketeer.repository.VenueRepository;
import com.ticketeer.service.VenueService;
import com.ticketeer.util.ObjectMapper;
import com.ticketeer.util.ValidationUtil;
import jakarta.persistence.PersistenceException;
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
    public GetVenuesOutput getVenues(VenueSearchConstraints getVenuesSearchConstraints) throws ServiceException {
        GetVenuesOutput getVenuesOutput = new GetVenuesOutput();

        System.out.println("Listing venues by constraints: " + getVenuesSearchConstraints);

        List<VenueDto> venuesList;

        try{
            //If the venue's ID is provided
            if(Objects.nonNull(getVenuesSearchConstraints.getId())){
                VenueDto venue = null;

                System.out.println("Listing venues by ID: " + getVenuesSearchConstraints.getId());
                Optional<VenueDto> venueDtoOptional = venueRepository.findById(getVenuesSearchConstraints.getId());

                if(venueDtoOptional.isPresent()){
                    venue = venueDtoOptional.get();
                    venuesList = new ArrayList<>();
                    venuesList.add(venue);
                    getVenuesOutput.setVenueList(venuesList);
                    return getVenuesOutput;
                }

            }

            //If the venue's name is provided
            if(Objects.nonNull(getVenuesSearchConstraints.getName()) && !getVenuesSearchConstraints.getName().isEmpty()){
                System.out.println("Listing venues by name");
                venuesList = venueRepository.findByVenueNameContainingIgnoreCase(getVenuesSearchConstraints.getName());
                getVenuesOutput.setVenueList(venuesList);
                return getVenuesOutput;
            }

            //If the venue's city is provided
            if(Objects.nonNull(getVenuesSearchConstraints.getCity()) && !getVenuesSearchConstraints.getCity().isEmpty()){
                System.out.println("Listing venues by city");
                venuesList = venueRepository.findByVenueCityContainingIgnoreCase(getVenuesSearchConstraints.getCity());
                getVenuesOutput.setVenueList(venuesList);
                return getVenuesOutput;
            }

            //If the request body is valid but has no values (constraints)
            System.out.println("Listing all venues");

            venuesList = venueRepository.findAll();
            getVenuesOutput.setVenueList(venuesList);

        } catch (PersistenceException err) {
            System.err.println("Error listing venues: " + err);
            throw new ServiceException(err);
        }

        return getVenuesOutput;
    }
}
