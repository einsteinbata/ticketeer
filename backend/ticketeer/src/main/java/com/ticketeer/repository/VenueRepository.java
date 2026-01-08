package com.ticketeer.repository;

import com.ticketeer.pojo.dto.VenueDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<VenueDto,Long> {

}
