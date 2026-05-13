package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.model.Event;
import lombok.Data;

@Data
public class AddEventOutput extends ServiceOutput {
    private Event event;
}
