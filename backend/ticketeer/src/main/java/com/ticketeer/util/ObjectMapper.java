package com.ticketeer.util;

import com.google.gson.Gson;
import com.ticketeer.pojo.dto.EventDto;
import com.ticketeer.pojo.dto.OrganizerDto;
import com.ticketeer.pojo.dto.VenueDto;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.model.Event;
import com.ticketeer.pojo.model.SeatArrangement;

public class ObjectMapper {

    public static VenueDto inputToDto(AddVenueInput addVenueInput){
        VenueDto venueDto = new VenueDto();

        venueDto.setVenueName(addVenueInput.getVenueName());
        venueDto.setVenueCity(addVenueInput.getVenueCity());
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
        event.setEventDate(event.getEventDate());
        event.setEventStatus(eventDto.getEventStatus());
        event.setOrganizerId(event.getOrganizerId());
        event.setMaxTicketsPerPurchase(eventDto.getMaxTicketsPerPurchase());

        return event;
    }

}
