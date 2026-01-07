package com.ticketeer.pojo.io;

import com.ticketeer.pojo.ServiceOutput;
import com.ticketeer.pojo.model.Organizer;
import lombok.Data;

import java.util.List;

@Data
public class GetOrganizersOutput extends ServiceOutput {
    private List<Organizer> organizers;
}
