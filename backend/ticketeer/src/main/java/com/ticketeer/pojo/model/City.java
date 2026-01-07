package com.ticketeer.pojo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class City {
    private Long cityId;
    private String city;
    private String country;
    private String zipCode;
}
