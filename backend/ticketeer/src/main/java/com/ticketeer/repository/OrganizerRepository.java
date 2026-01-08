package com.ticketeer.repository;

import com.ticketeer.pojo.dto.OrganizerDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<OrganizerDto,Long> {
}
