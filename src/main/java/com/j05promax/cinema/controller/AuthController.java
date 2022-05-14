package com.j05promax.cinema.controller;

import javax.servlet.http.Cookie;
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
		@RequestParam(value = "username", defaultValue = "") String username,
		@RequestParam(value = "password", defaultValue = "") String password,
		HttpServletRequest request,
		HttpServletResponse response
	) {
		// for(Cookie cookie : request.getCookies()) {
		// 	System.out.println(cookie.getName() + ": " + cookie.getValue());
		// }
		response.addCookie(new Cookie("name" , username));

		// TODO: handle login
		if (username.contentEquals("admin") && password.contentEquals("admin")) {
			return "redirect:/";
		} else {
			return "redirect:/auth/login";
		}

		// return "login";
	}

}
