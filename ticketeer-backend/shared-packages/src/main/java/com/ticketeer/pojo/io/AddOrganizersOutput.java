package com.ticketeer.pojo.io;

import com.ticketeer.pojo.dto.OrganizerDto;
import lombok.Data;

@Data
public class AddOrganizersOutput extends ServiceOutput {
    private OrganizerDto organizer;
}
