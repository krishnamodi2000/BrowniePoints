package com.GRP3.BPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BpaApplication {

	/**
	 * The main method is responsible for running the BPA application.
	 * It calls the SpringApplication.run() method to bootstrap the Spring Boot application.
	 * @param args the command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(BpaApplication.class, args);
	}
}
