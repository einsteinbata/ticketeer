package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceOutput;
import com.ticketeer.pojo.model.Organizer;
import lombok.Data;

@Data
public class AddOrganizersOutput extends ServiceOutput {
    private Organizer organizer;
}
