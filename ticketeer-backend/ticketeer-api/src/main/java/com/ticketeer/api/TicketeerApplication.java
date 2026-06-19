package com.ticketeer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.ticketeer.pojo"})
public class TicketeerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketeerApplication.class, args);
	}

}
