package com.ticketeer.repository;

import com.ticketeer.pojo.dto.OrganizerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerDto,Long> {
    List<OrganizerDto> findByOrganizerNameContainingIgnoreCase(String organizerName);
}
