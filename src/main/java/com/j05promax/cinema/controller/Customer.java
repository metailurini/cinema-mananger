package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j05promax.cinema.entity.User;
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

            @RequestParam(name = "phone_or_name", required = false, defaultValue = "") String searchString,
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            @RequestParam(value = "chon", required = false, defaultValue = "") String choose,
            Model model) {

        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) return "redirect:/auth/login";
            

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
        
        int counted = 0;
        try {
            counted = repo.User.CountCustomer(searchString, choose);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> users = new ArrayList<>();
        try {
            users = repo.User.GetAll(searchString.strip(),choose);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("staffName", "Staff's name");
        model.addAttribute("countedCustomer", counted);
        model.addAttribute("users", users);
        return "customer";
    }
}
