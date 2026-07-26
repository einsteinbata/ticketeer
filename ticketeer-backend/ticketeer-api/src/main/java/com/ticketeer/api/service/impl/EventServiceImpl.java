package com.ticketeer.api.service.impl;

import com.google.gson.Gson;
import com.ticketeer.api.constraints.EventSearchConstraints;
import com.ticketeer.api.repository.EventRepository;
import com.ticketeer.api.repository.OrganizerRepository;
import com.ticketeer.api.repository.VenueRepository;
import com.ticketeer.api.service.CachingService;
import com.ticketeer.api.util.CachingUtil;
import com.ticketeer.api.util.ObjectMapper;
import com.ticketeer.api.util.ValidationUtil;
import com.ticketeer.api.service.EventService;
import com.ticketeer.constants.CachingKey;
import com.ticketeer.constants.EventStatus;
import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.dto.EventDto;
import com.ticketeer.pojo.dto.OrganizerDto;
import com.ticketeer.pojo.dto.VenueDto;
import com.ticketeer.pojo.io.*;
import com.ticketeer.pojo.model.Event;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private OrganizerRepository organizerRepository;
    private VenueRepository venueRepository;
    private CachingService cachingService;

    @Autowired
    public EventServiceImpl(
            EventRepository eventRepository,
            OrganizerRepository organizerRepository,
            VenueRepository venueRepository,
            CachingService cachingService) {

        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.venueRepository = venueRepository;
        this.cachingService = cachingService;
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
        List<Event> allEvents = new ArrayList<>();

        try {
            //TODO add all constraints
            //TODO combine with event status constraints
            //TODO filter cached results by date

            List<CachingKey> queryCachingKeys = CachingUtil.getEventSearchCachingKeys(constraints);

            List<CachingKey> pendingQueryCachingKeys = new ArrayList<>(queryCachingKeys);

            List<Event> cachedEvents = new ArrayList<>();

            for (CachingKey cachingKey : queryCachingKeys) {

                String cachedString = cachingService.getResult(
                        CachingUtil.generateQueryKey(cachingKey, constraints)
                );

                if(Objects.isNull(cachedString))
                    continue;

                List<Event> localKeyCachedEvents = Arrays.stream(new Gson().fromJson(cachedString, Event[].class)).toList();

                if(!localKeyCachedEvents.isEmpty()){
                    System.out.println(localKeyCachedEvents.size() + " cached events found.");

                    localKeyCachedEvents.stream().forEach(e -> {cachedEvents.add(e);});

                    cachedEvents.addAll(localKeyCachedEvents);
                    pendingQueryCachingKeys.remove(cachingKey);
                }

            }

            System.out.println("Current remaing caching keys to be searched: \n");
            pendingQueryCachingKeys.forEach(System.out::println);


            //Adding cached results (if any) to the output object
            cachedEvents.forEach(event -> {
                getEventsOutput.getEventList().add(event);
            });

            if(Objects.isNull(getEventsOutput.getEventList())){
                System.out.println("No events found on the cache.");
            } else {
                System.out.println(getEventsOutput.getEventList().size() + " events from the cache.");
            }

            if(pendingQueryCachingKeys.isEmpty()){
                System.out.println("All keys were loaded from the cache. Skipping DB check.");
                return getEventsOutput;
            }

            //Finding on database whatever keys are still in the pendingQueryCachingKeys list

            //Finding by the Venue ID and then saving the results to the cache
            List<EventDto> eventsByVenueId = null;

            if(Objects.nonNull(constraints.getVenueId())){
                eventsByVenueId = eventRepository.findByFilters(constraints.getVenueId(), null, EventStatus.PRE_SALE.name());

                System.out.println("Number of events by VenueId: " + eventsByVenueId.size());

                if(!eventsByVenueId.isEmpty()){

                    cachingService.saveToCache(
                            CachingKey.VENUE.getKey() + ":" + constraints.getVenueId(),
                            new Gson().toJson(eventsByVenueId)
                    );

                    eventsByVenueId.forEach(event -> {
                        allEvents.add(ObjectMapper.dtoToModel(event));
                    });

                }
            }

            //Finding by the Organizer ID and then saving the results to the cache
            List<EventDto> eventsByOrganizerId = null;

            if(Objects.nonNull(constraints.getOrganizerId())){
                eventsByOrganizerId = eventRepository.findByFilters(null, constraints.getOrganizerId(), EventStatus.PRE_SALE.name());

                System.out.println("Number of events by OrganizerId: " + eventsByOrganizerId.size());

                if(!eventsByOrganizerId.isEmpty()){

                    cachingService.saveToCache(
                            CachingKey.ORGANIZER.getKey() + ":" + constraints.getOrganizerId(),
                            new Gson().toJson(eventsByOrganizerId)
                    );

                    eventsByOrganizerId.forEach(event -> {
                        allEvents.add(ObjectMapper.dtoToModel(event));
                    });

                }
            }


            if(Objects.isNull(getEventsOutput.getEventList())){
                System.out.println("No events found in either cache or DB.");
                getEventsOutput.setEventList(new ArrayList<>());
            } else {
                getEventsOutput.getEventList().addAll(allEvents);
                System.out.println(getEventsOutput.getEventList().size() + " events found before filtering");

                List<Event> filteredEvents =
                        filterEventOutputByConstraints(
                                getEventsOutput.getEventList(),
                                constraints
                        );

                System.out.println(filteredEvents.size() + " filtered events to be returned");

                getEventsOutput.setEventList(filteredEvents);
            }

        } catch (PersistenceException err) {
            System.err.println("Error listing events: " + err);
            throw new ServiceException(err);
        }

        return getEventsOutput;
    }

    private List<Event> filterEventOutputByConstraints(List<Event> allEvents, EventSearchConstraints constraints){

        Set<Event> filteredEvents = new HashSet<>();

        for (Event event : allEvents){

            if(event.getOrganizerId() != null && constraints.getOrganizerId() != null){
                if(event.getOrganizerId() == constraints.getOrganizerId())
                    filteredEvents.add(event);
            }

            if(event.getVenueId() != null && constraints.getVenueId() != null){
                if(event.getVenueId() == constraints.getVenueId())
                    filteredEvents.add(event);
            }

        }

        return new ArrayList<>(filteredEvents);
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
