package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.dto.OrganizerDto;
import lombok.Data;

@Data
public class AddOrganizersOutput extends ServiceOutput {
    private OrganizerDto organizer;
}
