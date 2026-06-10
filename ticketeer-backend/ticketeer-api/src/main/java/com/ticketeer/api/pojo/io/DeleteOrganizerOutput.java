package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteOrganizerOutput extends ServiceOutput {
    private Long organizerId;
}
