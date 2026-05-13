package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteOrganizerInput extends ServiceInput{
    private Long organizerId;
}
