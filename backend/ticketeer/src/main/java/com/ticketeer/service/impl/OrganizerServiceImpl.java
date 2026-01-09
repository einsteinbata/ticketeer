package com.ticketeer.service.impl;

import com.ticketeer.exceptions.FieldValidationError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.OrganizerSearchConstraints;
import com.ticketeer.pojo.dto.OrganizerDto;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddOrganizersOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;
import com.ticketeer.repository.OrganizerRepository;
import com.ticketeer.service.OrganizerService;
import com.ticketeer.util.ObjectMapper;
import com.ticketeer.util.ValidationUtil;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

            OrganizerDto organizerDto = ObjectMapper.inputToDto(addOrganizerInput);

            OrganizerDto savedOrganizer = organizerRepository.save(organizerDto);

            System.out.println("Organizer created: " + savedOrganizer.toString());

            addOrganizersOutput.setOrganizer(savedOrganizer);

        } catch (FieldValidationError err){
            System.err.println(err);
        }

        return addOrganizersOutput;
    }

    @Override
    public GetOrganizersOutput getOrganizers(OrganizerSearchConstraints constraints) throws ServiceException {
        GetOrganizersOutput getOrganizersOutput = new GetOrganizersOutput();

        List<OrganizerDto> organizerList;

        try{

            if(Objects.nonNull(constraints.getId())){
                OrganizerDto organizer = null;

                Optional<OrganizerDto> organizerOptional = organizerRepository.findById(constraints.getId());

                if(organizerOptional.isPresent()){
                    organizer = organizerOptional.get();
                    organizerList = new ArrayList<>();
                    organizerList.add(organizer);
                    getOrganizersOutput.setOrganizerList(organizerList);
                    return getOrganizersOutput;
                }
            }

            if(Objects.nonNull(constraints.getName()) && !constraints.getName().isEmpty()){
                organizerList = organizerRepository.findByOrganizerNameContainingIgnoreCase(constraints.getName());
                getOrganizersOutput.setOrganizerList(organizerList);
                return getOrganizersOutput;
            }

            System.out.println("Listing all organizers");

            organizerList = organizerRepository.findAll();
            getOrganizersOutput.setOrganizerList(organizerList);

        } catch (PersistenceException err) {
            System.err.println("Error listing organizers: " + err);
            throw new ServiceException(err);
        }

        return getOrganizersOutput;
    }

}
