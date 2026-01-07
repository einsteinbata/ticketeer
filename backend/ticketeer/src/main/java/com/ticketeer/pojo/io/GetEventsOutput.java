package com.ticketeer.pojo.io;

import com.ticketeer.pojo.model.Event;
import com.ticketeer.pojo.ServiceOutput;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsOutput extends ServiceOutput {
    private List<Event> eventList;
}
