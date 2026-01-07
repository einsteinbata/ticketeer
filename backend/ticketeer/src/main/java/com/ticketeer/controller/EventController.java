package com.ticketeer.controller;

import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.pojo.constraints.EventSearchConstraints;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;
import com.ticketeer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<AddEventOutput> addEvent(@RequestBody AddEventInput addEventInput){
        AddEventOutput addVenueOutput = null;

        try{
            addVenueOutput = eventService.addEvent(addEventInput);
        } catch (ResourceNotFoundError err){
            addVenueOutput = new AddEventOutput();
            addVenueOutput.setOperationStatus(2);
            addVenueOutput.setErrorMessage(err.getMessage());

            return ResponseEntity.badRequest().body(addVenueOutput);
        }

        return ResponseEntity.ok(addVenueOutput);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<GetEventsOutput> getEvents(@RequestBody EventSearchConstraints constraints){
        return ResponseEntity.ok(new GetEventsOutput());
    }

}
