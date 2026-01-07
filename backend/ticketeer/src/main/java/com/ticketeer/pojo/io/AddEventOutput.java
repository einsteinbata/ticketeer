package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceOutput;
import com.ticketeer.pojo.model.Event;
import lombok.Data;

@Data
public class AddEventOutput extends ServiceOutput {
    private Event event;
}
