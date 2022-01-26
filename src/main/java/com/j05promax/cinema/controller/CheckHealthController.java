package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckHealthController {

	@GetMapping("/check_health")
	// public String health(@RequestParam(name="status", required=false, defaultValue="okie") String status, Model model) {
		// model.addAttribute("status", status);
	public String health() {
		return "check_health";
	}

}
