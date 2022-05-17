package com.j05promax.cinema.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.service.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

	@GetMapping("/")
	public String Default(
			HttpServletRequest request,
			HttpServletResponse response) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}
		return "redirect:/main-page";
	}

	@GetMapping("/auth/login")
	public String Login(
			HttpServletRequest request,
			HttpServletResponse response) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (ctx.SignedIn) {
			return "redirect:/main-page";
		}
		return "login";
	}

	@PostMapping("/auth/auth")
	public String Auth(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(value = "email", defaultValue = "") String adminEmail,
			@RequestParam(value = "password", defaultValue = "") String password) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		Service service = Service.getInstance();
		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

		Admin foundedAdmin = null;
		try {
			foundedAdmin = repo.Admin.GetByEmail(adminEmail);
		} catch (SQLException e) {
			return "redirect:/auth/login";
		}

		if (foundedAdmin.AdminID.contentEquals("")) {
			return "redirect:/auth/login";
		}

		// if (username.contentEquals("admin") && password.contentEquals("admin")) {
		if (service.Admin.CheckCorrectPassword(foundedAdmin, password)) {
			Midleware.SignInToken(ctx, foundedAdmin.AdminID);
			return "redirect:/main-page";
		} else {
			return "redirect:/auth/login";
		}
	}

}
