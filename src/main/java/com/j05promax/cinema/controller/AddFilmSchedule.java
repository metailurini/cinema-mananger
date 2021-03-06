package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddFilmSchedule {
    @GetMapping("add-film-schedule")
    public String addFilmSchedule(Model model, HttpServletRequest request, HttpServletResponse response) {
        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!Midleware.IsSignedIn(ctx)) {
            return "redirect:/auth/login";
        }
        return "add-film-schedule";
    }
}
