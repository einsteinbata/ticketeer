package com.ticketeer.repository;

import com.ticketeer.pojo.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventDto,Long> {
}
