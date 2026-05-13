package com.ticketeer.api.controller;

import com.ticketeer.api.exceptions.ResourceNotFoundError;
import com.ticketeer.api.exceptions.ServiceException;
import com.ticketeer.api.pojo.constraints.EventSearchConstraints;
import com.ticketeer.api.util.ErrorResponseUtil;
import com.ticketeer.api.pojo.io.AddEventInput;
import com.ticketeer.api.pojo.io.AddEventOutput;
import com.ticketeer.api.pojo.io.DeleteEventInput;
import com.ticketeer.api.pojo.io.DeleteEventOutput;
import com.ticketeer.api.pojo.io.GetEventsOutput;
import com.ticketeer.api.service.EventService;
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
        AddEventOutput addEventOutput = null;

        try{
            addEventOutput = eventService.addEvent(addEventInput);
        } catch (ResourceNotFoundError | ServiceException err){
            addEventOutput = ErrorResponseUtil.generateForAddEvent(err);

            System.out.println("Add event output: " + addEventOutput);
            return ResponseEntity.badRequest().body(addEventOutput);
        }

        System.out.println("Add event output: " + addEventOutput);
        return ResponseEntity.ok(addEventOutput);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<GetEventsOutput> getEvents(@RequestBody EventSearchConstraints constraints){

        GetEventsOutput getEventsOutput = null;

        try{
            getEventsOutput = eventService.getEvents(constraints);
        } catch (ServiceException err) {
            System.err.println("Get venues error: " + err);

            getEventsOutput = ErrorResponseUtil.generateForGetEvents(err);
            return ResponseEntity.internalServerError().body(getEventsOutput);
        }

        return ResponseEntity.ok(getEventsOutput);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteEventOutput> deleteEvent(@RequestBody DeleteEventInput deleteEventInput){

        DeleteEventOutput output = null;

        try {
            output = eventService.deleteEvent(deleteEventInput.getEventId());
        } catch (ServiceException err){
            System.err.println(err);
            output = ErrorResponseUtil.generateForDeleteEvent(err);
            return ResponseEntity.internalServerError().body(output);
        }

        return ResponseEntity.ok(output);
    }

}
