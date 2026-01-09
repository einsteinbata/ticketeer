package com.ticketeer.controller;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.OrganizerSearchConstraints;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddOrganizersOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;
import com.ticketeer.service.OrganizerService;
import com.ticketeer.util.ErrorResponseUtil;
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

}
