package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmTicketingLastStep {
    @GetMapping("film-ticketing-last-step")
    public String filmTicketingLastStep(Model model, HttpServletRequest request, HttpServletResponse response) {
        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;

        ctx = Midleware.Authenticate(ctx);
        if (!ctx.SignedIn) {
            return "redirect:/auth/login";
        }
        return "film-ticketing-last-step";
    }
}
