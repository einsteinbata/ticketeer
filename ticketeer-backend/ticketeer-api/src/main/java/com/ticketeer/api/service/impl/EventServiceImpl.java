package com.ticketeer.api.service.impl;

import com.ticketeer.api.exceptions.ResourceNotFoundError;
import com.ticketeer.api.exceptions.ServiceException;
import com.ticketeer.api.pojo.constraints.EventSearchConstraints;
import com.ticketeer.api.pojo.dto.EventDto;
import com.ticketeer.api.pojo.dto.OrganizerDto;
import com.ticketeer.api.pojo.dto.VenueDto;
import com.ticketeer.api.pojo.io.AddEventInput;
import com.ticketeer.api.pojo.io.AddEventOutput;
import com.ticketeer.api.pojo.io.DeleteEventOutput;
import com.ticketeer.api.pojo.io.GetEventsOutput;
import com.ticketeer.api.pojo.model.Event;
import com.ticketeer.api.repository.EventRepository;
import com.ticketeer.api.repository.OrganizerRepository;
import com.ticketeer.api.repository.VenueRepository;
import com.ticketeer.api.util.ObjectMapper;
import com.ticketeer.api.util.ValidationUtil;
import com.ticketeer.api.exceptions.FieldValidationError;
import com.ticketeer.api.service.EventService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public AddEventOutput addEvent(AddEventInput addEventInput) throws ResourceNotFoundError, ServiceException {
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

        } catch (FieldValidationError | PersistenceException err) {
            System.err.println("Error adding event: " + err);
            throw new ServiceException(err);
        }

        return addEventOutput;
    }

    @Override
    public GetEventsOutput getEvents(EventSearchConstraints constraints) throws ServiceException {

        GetEventsOutput getEventsOutput = new GetEventsOutput();

        try {
            //TODO add all constraints
            //TODO combine with event status constraints



            System.out.println("Listing all events");

            List<EventDto> eventDtoList = eventRepository.findAll();
            List<Event> events = new ArrayList<>();

            for (EventDto eventDto : eventDtoList){
                Event event = ObjectMapper.dtoToModel(eventDto);
                events.add(event);
            }

            getEventsOutput.setEventList(events);
        } catch (PersistenceException err) {
            System.err.println("Error listing events: " + err);
            throw new ServiceException(err);
        }

        return getEventsOutput;
    }

    @Override
    public DeleteEventOutput deleteEvent(Long eventId) throws ServiceException {

        DeleteEventOutput output = null;

        System.out.println("Deleting event with ID " + eventId);

        try{
            eventRepository.deleteById(eventId);
            output = new DeleteEventOutput();
            output.setEventId(eventId);
        } catch (Exception err) {
            System.err.println("Error deleting event with ID " + eventId + "\n" + err);
            throw new ServiceException(err);
        }

        return output;
    }

}
