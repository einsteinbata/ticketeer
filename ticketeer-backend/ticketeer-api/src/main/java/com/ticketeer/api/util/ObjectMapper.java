package com.ticketeer.api.util;

import com.google.gson.Gson;
import com.ticketeer.api.pojo.dto.EventDto;
import com.ticketeer.api.pojo.dto.OrganizerDto;
import com.ticketeer.api.pojo.dto.VenueDto;
import com.ticketeer.api.pojo.io.AddEventInput;
import com.ticketeer.api.pojo.io.AddOrganizerInput;
import com.ticketeer.api.pojo.io.AddVenueInput;
import com.ticketeer.api.pojo.model.Event;
import com.ticketeer.api.pojo.model.SeatArrangement;

public class ObjectMapper {

    public static VenueDto inputToDto(AddVenueInput addVenueInput){
        VenueDto venueDto = new VenueDto();

        venueDto.setVenueName(addVenueInput.getVenueName());
        venueDto.setVenueCity(addVenueInput.getVenueCity());
        venueDto.setVenueAddress(addVenueInput.getVenueAddress());
        venueDto.setVenueCoordinates(addVenueInput.getVenueCoordinates());
        venueDto.setManagementEmail(addVenueInput.getManagementEmail());
        venueDto.setManagementPhone(addVenueInput.getManagementPhone());
        venueDto.setCapacity(addVenueInput.getCapacity());

        return venueDto;
    }

    public static OrganizerDto inputToDto(AddOrganizerInput addOrganizerInput){
        OrganizerDto organizerDto = new OrganizerDto();

        organizerDto.setOrganizerName(addOrganizerInput.getOrganizerName());
        organizerDto.setOrganizerEmail(addOrganizerInput.getOrganizerEmail());
        organizerDto.setOrganizerPhoneNumber(addOrganizerInput.getOrganizerPhoneNumber());
        organizerDto.setOrganizerAddress(addOrganizerInput.getOrganizerAddress());

        return organizerDto;
    }

    public static EventDto inputToDto(AddEventInput addEventInput){
        EventDto eventDto = new EventDto();

        eventDto.setSeatArrangementJson(addEventInput.getSeatArrangement().toJson());
        eventDto.setEventDate(addEventInput.getEventDate());
        eventDto.setMaxTicketsPerPurchase(addEventInput.getMaxTicketsPerPurchase());

        return eventDto;
    }

    public static Event dtoToModel(EventDto eventDto){
        Event event = new Event();

        event.setEventId(eventDto.getEventId());
        event.setFeatured(eventDto.isFeatured());
        event.setSeatArrangement(new Gson().fromJson(eventDto.getSeatArrangementJson(), SeatArrangement.class));
        event.setEventDate(eventDto.getEventDate());
        event.setEventStatus(eventDto.getEventStatus());
        event.setOrganizerId(eventDto.getOrganizerId());
        event.setMaxTicketsPerPurchase(eventDto.getMaxTicketsPerPurchase());

        return event;
    }

}
