package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RevenueController {

	@GetMapping("/revenue")
	public String FilterByFactor(
		HttpServletRequest request,
		HttpServletResponse response,

		@RequestParam(value = "type", defaultValue = "month") String type,
		@RequestParam(value = "value", defaultValue = "") String value
	) {
		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}
		
		switch(type) {
		case "month":
			// for month
			break;
		case "year":
			// for year
			break;
		default:
			// raise error
		}

		System.out.println("====[revenue type] " + type + "; " + "====[revenue value] " + value + request.getHeader("Accept"));
		return "revenue";
	}
}
