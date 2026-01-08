package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity(name = "organizer")
public class OrganizerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;

    @Column(nullable = false)
    private String organizerName;
    @Column
    private String organizerEmail;
    @Column
    private String organizerPhoneNumber;
    @Column
    private String organizerAddress;

}
