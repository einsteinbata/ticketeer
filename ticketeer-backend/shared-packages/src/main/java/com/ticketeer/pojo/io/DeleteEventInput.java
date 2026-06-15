package com.ticketeer.pojo.io;

import lombok.Data;

@Data
public class DeleteEventInput extends ServiceInput{
    private Long eventId;
}
