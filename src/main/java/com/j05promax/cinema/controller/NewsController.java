package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class NewsController {

	@GetMapping("/news")
	public String GetAllNews() {
		return "news";
	}

	@GetMapping("/news/{id}")
	public String GetNewsByIDs(@PathVariable(value="id") String id) {
		System.out.println("====[news id] " + id);
		return "news";
	}

	@PostMapping("/news/")
	public String CreateNews() {
		return "news";
	}
	
	@PutMapping("/news/{id}")
	public String UpdateNewsByID(@PathVariable(value="id") String id) {
		System.out.println("====[news id] " + id);
		return "news";
	}


	@DeleteMapping("/news/{id}")
	public String DeleteNewsByID(@PathVariable(value="id") String id) {
		System.out.println("====[news id] " + id);
		return "news";
	}
}
