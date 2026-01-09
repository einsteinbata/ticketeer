package com.ticketeer.repository;

import com.ticketeer.pojo.dto.VenueDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<VenueDto,Long> {
    List<VenueDto> findByCapacity(Integer capacity);
    List<VenueDto> findByVenueNameContainingIgnoreCase(String venueName);
    List<VenueDto> findByVenueCityContainingIgnoreCase(String venueCity);
}
