package com.ticketeer.api.pojo.io;

import com.ticketeer.api.pojo.dto.OrganizerDto;
import lombok.Data;

import java.util.List;

@Data
public class GetOrganizersOutput extends ServiceOutput {
    private List<OrganizerDto> organizerList;
}
