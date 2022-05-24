package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPage {
	@GetMapping("/main-page")
	public String mainpage(
			HttpServletRequest request,
			HttpServletResponse response,

			@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!Midleware.IsSignedIn(ctx)) {
			return "redirect:/auth/login";
        }
		model.addAttribute("staffName", ctx.UserEmail);
		return "main-page";
	}
}