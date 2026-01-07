package com.ticketeer.util;

import com.ticketeer.pojo.model.Venue;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.model.Event;
import com.ticketeer.pojo.model.Organizer;

public class ObjectMapper {

    //Maps AddVenueInput to Venue basic model
    public static Venue inputToModel(AddVenueInput addVenueInput){
        Venue venue = new Venue();

        venue.setVenueName(addVenueInput.getVenueName());
        venue.setVenueCity(addVenueInput.getVenueCity());
        venue.setVenueCoordinates(addVenueInput.getVenueCoordinates());
        venue.setManagementEmail(addVenueInput.getManagementEmail());
        venue.setManagementPhone(addVenueInput.getManagementPhone());
        venue.setCapacity(addVenueInput.getCapacity());

        return venue;
    }

    //Maps AddOrganizerInput to Organizer basic model
    public static Organizer inputToModel(AddOrganizerInput addOrganizerInput){
        Organizer organizer = new Organizer();

        organizer.setOrganizerName(addOrganizerInput.getOrganizerName());
        organizer.setOrganizerEmail(addOrganizerInput.getOrganizerEmail());
        organizer.setOrganizerPhoneNumber(addOrganizerInput.getOrganizerPhoneNumber());
        organizer.setOrganizerAddress(addOrganizerInput.getOrganizerAddress());

        return organizer;
    }

    //Maps AddEventInput to Event basic model
    public static Event inputToModel(AddEventInput addEventInput){
        Event event = new Event();

        event.setSeatArrangement(addEventInput.getSeatArrangement());
        event.setEventDate(addEventInput.getEventDate());
        event.setMaxTicketsPerPurchase(addEventInput.getMaxTicketsPerPurchase());

        return event;
    }

}
