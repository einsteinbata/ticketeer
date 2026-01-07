package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddOrganizersOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;
import com.ticketeer.pojo.model.Organizer;
import com.ticketeer.repository.OrganizerRepository;
import com.ticketeer.service.OrganizerService;
import com.ticketeer.util.ObjectMapper;
import com.ticketeer.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    private OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    @Override
    public AddOrganizersOutput addOrganizer(AddOrganizerInput addOrganizerInput) {
        AddOrganizersOutput addOrganizersOutput = new AddOrganizersOutput();

        System.out.println("Adding organizer: " + addOrganizerInput);

        try {
            ValidationUtil.validateAddOrganizerInput(addOrganizerInput);

            Organizer organizer = ObjectMapper.inputToModel(addOrganizerInput);

            Organizer savedOrganizer = organizerRepository.saveOrganizer(organizer);

            System.out.println("Organizer created: " + savedOrganizer.toString());

            addOrganizersOutput.setOrganizer(savedOrganizer);

        } catch (FieldValidationError err){
            System.err.println(err);
        }

        return addOrganizersOutput;
    }

    @Override
    public GetOrganizersOutput getOrganizers() {
        return null;
    }

}
