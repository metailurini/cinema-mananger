package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	@PostMapping("/auth/login")
	public String Login() {
		return "auth";
	}

}
