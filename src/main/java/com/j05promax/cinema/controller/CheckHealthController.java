package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;

import com.j05promax.cinema.repo.LocationRepo;
import com.j05promax.cinema.entity.Location;
import com.j05promax.cinema.util.db.PostgreSQL;
import com.j05promax.cinema.util.log.Log;

@Controller
public class CheckHealthController {

	@GetMapping("/check_health")
	// public String health(@RequestParam(name="status", required=false, defaultValue="okie") String status, Model model) {
	// model.addAttribute("status", status);
	public String health() {
		PostgreSQL postgreSQL = new PostgreSQL();
		try {
			postgreSQL.Open("jdbc:postgresql://localhost:5432/bob?user=postgres&password=example");
			LocationRepo locationRepo = new LocationRepo(postgreSQL);
			ArrayList<Location> a = locationRepo.GetAll();
			for (Location l : a) {
				System.out.println(l.toString());
			}
		} catch (SQLException e) {
            new Log(e).Show();
		}
		return "check_health";
	}

}
