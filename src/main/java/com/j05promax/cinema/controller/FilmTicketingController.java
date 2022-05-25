package com.j05promax.cinema.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.entity.Calendar;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.util.log.Log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FilmTicketingController {
    @GetMapping("/film/{id}")
    public String filmTicketing(
            HttpServletRequest request,
            HttpServletResponse response,

            @PathVariable String id,
            Model model) {
        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!Midleware.IsSignedIn(ctx)) {
            return "redirect:/auth/login";
        }

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
        ArrayList<Calendar> calendars = null;

        try {
            calendars = repo.Calendar.GetAll(id);
            System.out.println(":: " +calendars.size());
        } catch (SQLException e) {
            new Log(e).Show();
        }

        ArrayList<String> dates = new ArrayList<>();
        for (Calendar cal : calendars) {
            dates.add(String.valueOf(cal.StartTime.getTime()));
        }

        ctx.SetUnicodeCookie("calendar-dates", String.join(".", dates), "/");
        model.addAttribute("staffName", ctx.UserEmail);
        return "film-ticketing";
    }
}
