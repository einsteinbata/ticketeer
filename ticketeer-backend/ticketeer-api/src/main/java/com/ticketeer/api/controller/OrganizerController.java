package com.ticketeer.api.controller;

import com.ticketeer.api.exceptions.ServiceException;
import com.ticketeer.api.pojo.constraints.OrganizerSearchConstraints;
import com.ticketeer.api.pojo.io.*;
import com.ticketeer.api.util.ErrorResponseUtil;
import com.ticketeer.api.pojo.io.*;
import com.ticketeer.api.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService){
        this.organizerService = organizerService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddOrganizersOutput> addOrganizer(@RequestBody AddOrganizerInput addOrganizerInput){
        AddOrganizersOutput addOrganizersOutput = organizerService.addOrganizer(addOrganizerInput);
        return ResponseEntity.ok(addOrganizersOutput);
    }

    @GetMapping("/list")
    public ResponseEntity<GetOrganizersOutput> getOrganizers(@RequestBody OrganizerSearchConstraints constraints){
        GetOrganizersOutput getOrganizersOutput = null;

        try {
            getOrganizersOutput = organizerService.getOrganizers(constraints);
        } catch (ServiceException err) {
            System.err.println("Get organizers error: " + err);

            getOrganizersOutput = ErrorResponseUtil.generateForGetOrganizers(err);
            return ResponseEntity.internalServerError().body(getOrganizersOutput);
        } catch (Exception err) {
            System.err.println("Error: " + err);

            getOrganizersOutput = ErrorResponseUtil.generateForGetOrganizers(err);
            return ResponseEntity.internalServerError().body(getOrganizersOutput);
        }

        System.out.println("Get organizers output: " + getOrganizersOutput);
        return ResponseEntity.ok(getOrganizersOutput);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteOrganizerOutput> deleteOrganizer(@RequestBody DeleteOrganizerInput deleteOrganizerInput){

        DeleteOrganizerOutput output = null;

        try {
            output = organizerService.deleteOrganizer(deleteOrganizerInput.getOrganizerId());
        } catch (ServiceException err) {
            System.err.println(err);
            output = ErrorResponseUtil.generateForDeleteOrganizer(err);
            return ResponseEntity.internalServerError().body(output);
        }

        return ResponseEntity.ok(output);
    }


}
