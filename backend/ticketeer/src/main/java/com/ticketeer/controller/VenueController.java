package com.ticketeer.controller;

import com.ticketeer.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;
import com.ticketeer.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venue")
public class VenueController {

    private VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddVenueOutput> addVenue(@RequestBody AddVenueInput addVenueInput){

        AddVenueOutput addVenueOutput = venueService.addVenue(addVenueInput);
        return ResponseEntity.ok(addVenueOutput);
    }

    @GetMapping("/list")
    public ResponseEntity<GetVenuesOutput> getVenues(@RequestBody VenueSearchConstraints constraints){

        GetVenuesOutput getVenuesOutput = venueService.getVenues(constraints);
        return ResponseEntity.ok(getVenuesOutput);
    }

}
