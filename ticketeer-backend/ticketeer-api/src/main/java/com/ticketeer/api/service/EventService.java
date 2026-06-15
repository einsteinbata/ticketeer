package com.ticketeer.api.service;

import com.ticketeer.api.constraints.EventSearchConstraints;
import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.DeleteEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;

public interface EventService {
    AddEventOutput addEvent(AddEventInput addEventInput) throws ResourceNotFoundError, ServiceException;
    GetEventsOutput getEvents(EventSearchConstraints constraints) throws ServiceException;
    DeleteEventOutput deleteEvent(Long eventId) throws ServiceException;
}
