package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j05promax.cinema.entity.Event;
import com.j05promax.cinema.entity.Image;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.repo.Repository;
import com.j05promax.cinema.util.log.Log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

@Controller
public class NewsController {

	@GetMapping("/news")
	public String GetAllNews(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(name = "name", required = false, defaultValue = "") String search) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}
		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		List<Event> events = new ArrayList<>();
		try {
			events = repo.Event.GetAll(search.strip());
		} catch (SQLException e) {
            new Log(e).Show();
		}

		int counted = 0;
		try {
			counted = repo.Event.CountEvent();
		} catch (SQLException e) {
            new Log(e).Show();
		}

		model.addAttribute("events", events);
		model.addAttribute("countedEvent", counted);
		model.addAttribute("staffName", ctx.UserEmail);
		return "news";
	}

	@GetMapping("/news/{id}")
	public String GetNewsByIDs(
			HttpServletRequest request,
			HttpServletResponse response,

			@PathVariable(value = "id") String id) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}

		return "news";
	}

	@PostMapping("/news")
	public String CreateNews(
		HttpServletRequest request,
        HttpServletResponse response,

		@RequestParam(name = "tieu-de", required = false, defaultValue = "") String tieude,
		@RequestParam(name = "lk-anh", required = false, defaultValue = "") String lkanh,
		@RequestParam(name = "noi-dung", required = false, defaultValue = "") String noidung
	) {
		Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) return "redirect:/auth/login";

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
		Event event = new Event();
		Image image = new Image();
		event.Title = tieude;
		image.Url = lkanh;
		event.Content = noidung;
		image.ImageType = "events";

		if (!repo.Event.Create(event, image)) {
            return "error";
        }

		return "redirect:/news";
	}

	@PostMapping("/delete-news/{id}")
	public String DeleteNewsByID(
		HttpServletRequest request,
        HttpServletResponse response,

		@PathVariable(value = "id") String id
		) {
			Context ctx = new Context();
        	ctx.request = request;
        	ctx.response = response;

        	ctx = Midleware.Authenticate(ctx);
        	if (!ctx.SignedIn) return "redirect:/auth/login";

			PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
			Event event = new Event();
			Image image = new Image();
			event.EventID = id;

			if (!repo.Event.Delete(event, image)) {
				return "error";
			}
			return "redirect:/news";
	}

	@PostMapping("/news/{id}")
	public String UpdateNewsByID(
		HttpServletRequest request,
        HttpServletResponse response,

		@PathVariable(value = "id") String id,
		@RequestParam(name = "tieu-de", required = false, defaultValue = "") String tieude,
		@RequestParam(name = "lk-anh", required = false, defaultValue = "") String lkanh,
		@RequestParam(name = "noi-dung", required = false, defaultValue = "") String noidung
		) {
		Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) return "redirect:/auth/login";

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
		Event event = new Event();
		Image image = new Image();
		event.EventID = id;
		event.Title = tieude;
		image.Url = lkanh;
		event.Content = noidung;

		Repository.Error err = repo.Event.Update(event,image);
        if (err != null) {
			AuthController.setError(ctx, err.message);
        }

		return "redirect:/news";
	}
}
