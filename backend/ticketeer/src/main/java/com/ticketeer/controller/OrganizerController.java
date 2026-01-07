package com.ticketeer.controller;

import com.ticketeer.pojo.constraints.OrganizerSearchConstraints;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddOrganizersOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;
import com.ticketeer.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(new GetOrganizersOutput());
    }

}
