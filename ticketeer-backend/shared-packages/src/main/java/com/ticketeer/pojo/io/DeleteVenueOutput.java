package com.ticketeer.pojo.io;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class DeleteVenueOutput extends ServiceOutput{
    private Long venueId;

}
