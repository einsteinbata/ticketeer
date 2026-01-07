package com.ticketeer.repository;

import com.ticketeer.pojo.model.Organizer;

public interface OrganizerRepository {
    Organizer getOrganizerById(Long organizerId);
    Organizer saveOrganizer(Organizer organizer);
}
