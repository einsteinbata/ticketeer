package com.ticketeer.ticketeer_purchases_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.ticketeer.pojo"})
public class TicketeerPurchasesWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketeerPurchasesWorkerApplication.class, args);
	}

}
