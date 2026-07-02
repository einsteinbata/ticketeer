package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "organizer")
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
