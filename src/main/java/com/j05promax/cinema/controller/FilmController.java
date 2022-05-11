package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class FilmController {

	@GetMapping("/film")
	public String GetAllFilms() {
		return "film";
	}

	@GetMapping("/film/{id}")
	public String GetFilmByID(@PathVariable String id) {
		System.out.println("====[films id] " + id);
		return "film";
	}

	@PostMapping("/film/")
	public String CreateFilm() {
		return "film";
	}
	
	@PutMapping("/film/{id}")
	public String UpdateFilmByID(@PathVariable String id) {
		System.out.println("====[films id] " + id);
		return "film";
	}


	@DeleteMapping("/film/{id}")
	public String DeleteFilmByID(@PathVariable String id) {
		System.out.println("====[films id] " + id);
		return "film";
	}
}
