package com.j05promax.cinema.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.util.log.Log;

import org.springframework.ui.Model;

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
			HttpServletResponse response,
			Model model) {

		Context ctx = new Context();
		ctx.request = request;
		ctx.response = response;

		ctx = Midleware.Authenticate(ctx);
		if (!Midleware.IsSignedIn(ctx)) {
			return "redirect:/auth/login";
        }

		PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
		int counted = 0;
		try {
            counted = repo.Admin.CountAdmin();
        } catch (SQLException e) {
            new Log(e).Show();
        }

		ArrayList<Admin> admin = new ArrayList<>();
		try{
			admin = repo.Admin.GetAll();
		}
		catch(SQLException e){
			new Log(e).Show();
		}

		model.addAttribute("staffName", ctx.UserEmail);
		model.addAttribute("admin", admin);
		model.addAttribute("countedAdmin", counted);

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
