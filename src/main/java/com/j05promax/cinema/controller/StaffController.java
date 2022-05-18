package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class StaffController {

	@GetMapping("/staff")
	public String GetAllStaffs(
			HttpServletRequest request,
			HttpServletResponse response) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!ctx.SignedIn) {
			return "redirect:/auth/login";
		}

		return "staff";
	}

	@GetMapping("/staff/{id}")
	public String GetStaffByID(
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

		return "staff";
	}

	@PostMapping("/staff/")
	public String CreateStaff() {
		return "staff";
	}
	
	@PutMapping("/staff/{id}")
	public String UpdateStaffByID(@PathVariable String id) {
		return "staff";
	}


	@DeleteMapping("/staff/{id}")
	public String DeleteStaffByID(@PathVariable String id) {
		return "staff";
	}
}
