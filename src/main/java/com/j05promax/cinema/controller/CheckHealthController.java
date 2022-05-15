package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckHealthController {

	@GetMapping("/check_health")
	public String health(
		HttpServletRequest request,
		HttpServletResponse response,
		Model model
	) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);

		model.addAttribute("signedin", ctx.SignedIn);
		return "check_health";
	}

}
