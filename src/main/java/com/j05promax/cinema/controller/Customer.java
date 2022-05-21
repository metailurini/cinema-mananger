package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.j05promax.cinema.entity.User;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.repo.Repository;
import com.j05promax.cinema.util.log.Log;

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

            @RequestParam(name = "phone_or_name", required = false, defaultValue = "") String nameOrPhone,
            @RequestParam(name = "status", required = false, defaultValue = "") String status,
            @RequestParam(name = "page", required = false, defaultValue = "0") String sPage,
            Model model) {

        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx.SetUnicodeCookie("status", status,  "/customer");
        ctx.SetUnicodeCookie("phone_or_name", nameOrPhone,  "/customer");


        ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) return "redirect:/auth/login";

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
        int counted = 0;
        try {
            counted = repo.User.CountCustomer(nameOrPhone.strip(), status);
        } catch (SQLException e) {
            new Log(e).Show();
        }

        int page = 0;
        try {
            page = Integer.parseInt(sPage);
        } catch(NumberFormatException e) {
            new Log(e).Show();
        }

        Repository.Perpage perpage = new Repository.Perpage(page);
        Boolean[] listActivepage = new Boolean[(int) Math.ceil(counted * 1.0 / perpage.maxInPage)];
        Arrays.fill(listActivepage, false);
        if (listActivepage.length != 0) {
            listActivepage[perpage.page] = true;
        }

        List<User> users = new ArrayList<>();
        try {
            users = repo.User.GetAll(
                nameOrPhone.strip(),
                status,
                perpage
            );
        } catch (SQLException e) {
            new Log(e).Show();
        }
       
       
        model.addAttribute("countedCustomer", counted);
        model.addAttribute("pages", listActivepage);
        model.addAttribute("staffName", ctx.UserEmail);
        model.addAttribute("users", users);
        return "customer";
    }
}
