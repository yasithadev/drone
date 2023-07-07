package com.example.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneApplication.class, args);
	}
	/*
	 To kill any process listening to the port 8080:
		kill $(lsof -t -i:8080)
		
	or more violently:
		kill -9 $(lsof -t -i:8080)
	 */
}
