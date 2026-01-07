package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.pojo.model.Venue;
import com.ticketeer.pojo.constraints.EventSearchConstraints;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;
import com.ticketeer.pojo.model.Event;
import com.ticketeer.pojo.model.Organizer;
import com.ticketeer.repository.EventRepository;
import com.ticketeer.repository.OrganizerRepository;
import com.ticketeer.repository.VenueRepository;
import com.ticketeer.service.EventService;
import com.ticketeer.util.ObjectMapper;
import com.ticketeer.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private OrganizerRepository organizerRepository;
    private VenueRepository venueRepository;

    @Autowired
    public EventServiceImpl(
            EventRepository eventRepository,
            OrganizerRepository organizerRepository,
            VenueRepository venueRepository) {

        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public AddEventOutput addEvent(AddEventInput addEventInput) throws ResourceNotFoundError{
        AddEventOutput addEventOutput = new AddEventOutput();

        try {
            System.out.println("Adding event: " + addEventInput.toString());
            ValidationUtil.validateAddEventInput(addEventInput);

            Organizer organizer = organizerRepository.getOrganizerById(addEventInput.getOrganizerId());
            if(Objects.isNull(organizer))
                throw new ResourceNotFoundError("Could not find organizer for ID " + addEventInput.getOrganizerId());

            Venue venue = venueRepository.getVenueById(addEventInput.getVenueId());
            if(Objects.isNull(venue))
                throw new ResourceNotFoundError("Could not find venue for ID " + addEventInput.getVenueId());

            Event event = ObjectMapper.inputToModel(addEventInput);
            event.setVenue(venue);
            event.setOrganizer(organizer);

            Event savedEvent = eventRepository.saveEvent(event);

            System.out.println("Event created: " + savedEvent.toString());

            addEventOutput.setEvent(savedEvent);

        } catch (FieldValidationError err) {
            System.err.println(err);
        }

        return addEventOutput;
    }

    @Override
    public GetEventsOutput getEvents(EventSearchConstraints constraints) {
        return null;
    }
}
