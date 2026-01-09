package com.ticketeer.controller;

import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.EventSearchConstraints;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;
import com.ticketeer.service.EventService;
import com.ticketeer.util.ErrorResponseUtil;
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

}
