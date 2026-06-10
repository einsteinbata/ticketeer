package com.ticketeer.api.repository;

import com.ticketeer.api.pojo.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventDto,Long> {
}
