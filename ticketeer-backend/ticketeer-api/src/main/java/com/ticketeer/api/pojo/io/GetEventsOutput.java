package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.model.Event;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsOutput extends ServiceOutput {
    private List<Event> eventList;
}
