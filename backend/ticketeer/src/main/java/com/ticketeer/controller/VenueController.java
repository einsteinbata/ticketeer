package com.ticketeer.controller;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.VenueSearchConstraints;
import com.ticketeer.pojo.io.AddVenueInput;
import com.ticketeer.pojo.io.AddVenueOutput;
import com.ticketeer.pojo.io.GetVenuesOutput;
import com.ticketeer.service.VenueService;
import com.ticketeer.util.ErrorResponseUtil;
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
        System.out.println("Add venue output: " + addVenueOutput.toString());
        return ResponseEntity.ok(addVenueOutput);
    }

    @GetMapping("/list")
    public ResponseEntity<GetVenuesOutput> getVenues(@RequestBody VenueSearchConstraints constraints){
        GetVenuesOutput getVenuesOutput = null;

        try {
            getVenuesOutput = venueService.getVenues(constraints);

        } catch (ServiceException err) {
            System.err.println("Get venues error: " + err);

            getVenuesOutput = ErrorResponseUtil.generateForGetVenues(err);
            return ResponseEntity.internalServerError().body(getVenuesOutput);
        } catch (Exception err) {
            System.err.println("Error: " + err);

            getVenuesOutput = ErrorResponseUtil.generateForGetVenues(err);
            return ResponseEntity.internalServerError().body(getVenuesOutput);
        }

        System.out.println("Get venues output: " + getVenuesOutput);

        return ResponseEntity.ok(getVenuesOutput);
    }

}
