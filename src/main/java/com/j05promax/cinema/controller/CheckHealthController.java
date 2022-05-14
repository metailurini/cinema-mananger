package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.j05promax.cinema.config.Config;
import com.j05promax.cinema.entity.Film;

@Controller
public class CheckHealthController {

	@GetMapping("/check_health")
	public String health() {
		Config c = Config.getInstance();
		try {
			for (Film s : c.filmRepo.GetAll()) {
				System.out.println(s.toString());
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		return "check_health";
	}

}
