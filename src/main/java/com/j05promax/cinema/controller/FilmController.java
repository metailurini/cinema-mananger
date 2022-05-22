package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j05promax.cinema.entity.Film;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.util.log.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class FilmController {

	@GetMapping("/film")
	public String GetAllFilms(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(name = "name", required = false, defaultValue = "") String search,
			Model model) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn)
			return "redirect:/auth/login";

		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		int counted = 0;
		try {
			counted = repo.Film.CountFilmActive();
		} catch (SQLException e) {
            new Log(e).Show();
		}

		List<Film> films = new ArrayList<>();
		try {
			films = repo.Film.GetAll(search.strip());
		} catch (SQLException e) {
            new Log(e).Show();
		}

		model.addAttribute("films", films);
		model.addAttribute("countedFilm", counted);
		model.addAttribute("staffName", ctx.UserEmail);
		return "film";
	}

	@GetMapping("/film/{id}")
	public String GetFilmByID(
			HttpServletRequest request,
			HttpServletResponse response,

			@PathVariable String id) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}

		return "film";
	}

	@PostMapping("/film/")
	public String CreateFilm() {
		return "film";
	}

	@PutMapping("/film/{id}")
	public String UpdateFilmByID(@PathVariable String id) {
		return "film";
	}

	@DeleteMapping("/film/{id}")
	public String DeleteFilmByID(@PathVariable String id) {
		return "film";
	}
}
