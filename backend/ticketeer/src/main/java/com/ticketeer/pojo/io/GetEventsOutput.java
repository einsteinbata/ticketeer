package com.ticketeer.pojo.io;

import com.ticketeer.pojo.dto.EventDto;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsOutput extends ServiceOutput {
    private List<EventDto> eventList;
}
