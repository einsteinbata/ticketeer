package com.ticketeer.api.service;

import com.ticketeer.api.constraints.EventSearchConstraints;
import com.ticketeer.api.service.impl.EventServiceImpl;
import com.ticketeer.constants.CachingKey;
import com.ticketeer.exceptions.ResourceNotFoundError;
import com.ticketeer.exceptions.ServiceException;
import com.ticketeer.pojo.io.AddEventInput;
import com.ticketeer.pojo.io.AddEventOutput;
import com.ticketeer.pojo.io.DeleteEventOutput;
import com.ticketeer.pojo.io.GetEventsOutput;

import java.util.List;

public interface EventService {
    AddEventOutput addEvent(AddEventInput addEventInput) throws ResourceNotFoundError, ServiceException;
    GetEventsOutput getEvents(EventSearchConstraints constraints) throws ServiceException;
    DeleteEventOutput deleteEvent(Long eventId) throws ServiceException;
}
