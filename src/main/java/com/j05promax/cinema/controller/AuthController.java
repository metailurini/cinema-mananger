package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

	@GetMapping("/auth/login")
	public String Login() {
		return "login";
	}

	@PostMapping("/auth/auth")
	public String Auth(
		HttpServletRequest request,
		HttpServletResponse response,

		@RequestParam(value = "username", defaultValue = "") String username,
		@RequestParam(value = "password", defaultValue = "") String password
	) {
		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		// TODO: handle check auth and find user id
		if (username.contentEquals("admin") && password.contentEquals("admin")) {
			String adminID = "adminID";
			Midleware.SignInToken(ctx, adminID);
			return "redirect:/main-page";
		} else {
			return "redirect:/auth/login";
		}
	}

}
