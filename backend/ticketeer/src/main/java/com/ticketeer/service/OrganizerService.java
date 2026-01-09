package com.ticketeer.service;

import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.constraints.OrganizerSearchConstraints;
import com.ticketeer.pojo.io.AddOrganizerInput;
import com.ticketeer.pojo.io.AddOrganizersOutput;
import com.ticketeer.pojo.io.GetOrganizersOutput;

public interface OrganizerService {
    AddOrganizersOutput addOrganizer(AddOrganizerInput addOrganizerInput);
    GetOrganizersOutput getOrganizers(OrganizerSearchConstraints constraints) throws ServiceException;
}
