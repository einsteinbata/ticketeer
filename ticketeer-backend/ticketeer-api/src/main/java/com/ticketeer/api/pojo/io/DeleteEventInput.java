package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteEventInput extends ServiceInput{
    private Long eventId;
}
