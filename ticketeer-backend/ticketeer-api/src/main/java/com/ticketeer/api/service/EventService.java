package com.ticketeer.api.service;

import com.ticketeer.api.exceptions.ResourceNotFoundError;
import com.ticketeer.api.exceptions.ServiceException;
import com.ticketeer.api.pojo.constraints.EventSearchConstraints;
import com.ticketeer.api.pojo.io.AddEventInput;
import com.ticketeer.api.pojo.io.AddEventOutput;
import com.ticketeer.api.pojo.io.DeleteEventOutput;
import com.ticketeer.api.pojo.io.GetEventsOutput;

public interface EventService {
    AddEventOutput addEvent(AddEventInput addEventInput) throws ResourceNotFoundError, ServiceException;
    GetEventsOutput getEvents(EventSearchConstraints constraints) throws ServiceException;
    DeleteEventOutput deleteEvent(Long eventId) throws ServiceException;
}
