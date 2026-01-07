package com.ticketeer.pojo.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "organizer")
public class OrganizerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventDto> events;

    @Column(nullable = false)
    private String organizerName;
    @Column
    private String organizerEmail;
    @Column
    private String organizerPhoneNumber;
    @Column
    private String organizerAddress;
}
