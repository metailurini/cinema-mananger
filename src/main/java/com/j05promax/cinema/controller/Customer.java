package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import com.j05promax.cinema.repo.PostgreSQLRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Customer {
    @GetMapping("/customer")
    public String customer(
            HttpServletRequest request,
            HttpServletResponse response,

            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {

        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) {
            return "redirect:/auth/login";
        }

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
