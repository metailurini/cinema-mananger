package com.j05promax.cinema;

import com.j05promax.cinema.repo.PostgreSQLRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaManagerApplication.class, args);
		PostgreSQLRepo.getInstance();
	}
}
