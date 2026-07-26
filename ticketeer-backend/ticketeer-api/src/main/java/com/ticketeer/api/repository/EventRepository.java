package com.ticketeer.api.repository;

import com.ticketeer.constants.EventStatus;
import com.ticketeer.pojo.dto.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventDto,Long> {
    @Query("""
        SELECT e FROM EventDto e
        WHERE (:venueId IS NULL OR e.venueId = :venueId)
        AND (:organizerId IS NULL OR e.organizerId = :organizerId)
        AND (:eventStatus IS NULL OR e.eventStatus = :eventStatus)
    """)
    List<EventDto> findByFilters(
            @Param("venueId") Long venueId,
            @Param("organizerId") Long organizerId,
            @Param("eventStatus") String eventStatus
    );

}
