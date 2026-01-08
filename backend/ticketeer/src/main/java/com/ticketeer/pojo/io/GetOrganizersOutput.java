package com.ticketeer.pojo.io;

import com.ticketeer.pojo.dto.OrganizerDto;
import lombok.Data;

import java.util.List;

@Data
public class GetOrganizersOutput extends ServiceOutput {
    private List<OrganizerDto> organizers;
}
