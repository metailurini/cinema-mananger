package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class StaffController {

	@GetMapping("/staff")
	public String GetAllStaffs() {
		return "staff";
	}

	@GetMapping("/staff/{id}")
	public String GetStaffByID(@PathVariable String id) {
		System.out.println("====[staff id] " + id);
		return "staff";
	}

	@PostMapping("/staff/")
	public String CreateStaff() {
		return "staff";
	}
	
	@PutMapping("/staff/{id}")
	public String UpdateStaffByID(@PathVariable String id) {
		System.out.println("====[staff id] " + id);
		return "staff";
	}


	@DeleteMapping("/staff/{id}")
	public String DeleteStaffByID(@PathVariable String id) {
		System.out.println("====[staff id] " + id);
		return "staff";
	}
}
