package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.pojo.dto.EventDto;
import com.ticketeer.pojo.dto.OrganizerDto;
import com.ticketeer.pojo.dto.VenueDto;
import com.ticketeer.pojo.constraints.EventSearchConstraints;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;
import com.ticketeer.pojo.model.Event;
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

            OrganizerDto organizerDto = organizerRepository.getReferenceById(addEventInput.getOrganizerId());
            if(Objects.isNull(organizerDto))
                throw new ResourceNotFoundError("Could not find organizer for ID " + addEventInput.getOrganizerId());

            VenueDto venueDto = venueRepository.getReferenceById(addEventInput.getVenueId());
            if(Objects.isNull(venueDto))
                throw new ResourceNotFoundError("Could not find venue for ID " + addEventInput.getVenueId());

            EventDto eventDto = ObjectMapper.inputToDto(addEventInput);
            eventDto.setVenueId(venueDto.getVenueId());
            eventDto.setOrganizerId(organizerDto.getOrganizerId());

            EventDto savedEvent = eventRepository.save(eventDto);
            System.out.println("Event created: " + savedEvent.toString());

            Event event = ObjectMapper.dtoToModel(savedEvent);
            System.out.println("Event mapped to Output: " + event);

            addEventOutput.setEvent(event);

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
