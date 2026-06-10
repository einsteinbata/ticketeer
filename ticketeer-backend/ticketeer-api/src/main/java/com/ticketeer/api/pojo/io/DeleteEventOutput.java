package com.ticketeer.api.pojo.io;

import lombok.Data;

@Data
public class DeleteEventOutput extends ServiceOutput {
    private Long eventId;
}
