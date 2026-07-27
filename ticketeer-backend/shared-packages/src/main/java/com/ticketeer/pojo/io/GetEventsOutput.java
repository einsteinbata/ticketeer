package com.ticketeer.pojo.io;

import com.ticketeer.pojo.model.Event;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GetEventsOutput extends ServiceOutput {
    private List<Event> eventList;

    public GetEventsOutput() {
        this.eventList = new ArrayList<>();
    }

    public GetEventsOutput(List<Event> eventList) {
        this.eventList = eventList;
    }
}
