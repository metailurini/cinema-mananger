package com.j05promax.cinema.controller;

import java.sql.SQLException;

import com.j05promax.cinema.repo.PostgreSQLRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Customer {
    @GetMapping("/customer")
    public String customer(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        PostgreSQLRepo postgreSQL = PostgreSQLRepo.getInstance();
        
        model.addAttribute("staffName", "Staff's name");
        int counted = 0;
        try {
            counted = postgreSQL.User.Count_Customer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("countedCustomer", counted);

        return "customer";
    }
}
