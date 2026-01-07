package com.ticketeer.repository.impl;

import com.ticketeer.pojo.model.Organizer;
import com.ticketeer.repository.OrganizerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizerRepositoryImpl implements OrganizerRepository {
    @Override
    public Organizer getOrganizerById(Long organizerId) {
        //TODO implement this
//        return new Organizer();
        return null;
    }

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        Organizer savedOrganizer = organizer;
        savedOrganizer.setOrganizerId(67L);
        return savedOrganizer;
    }
}
