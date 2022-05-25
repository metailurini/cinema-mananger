package com.j05promax.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmTicketingSecondStep {
    @GetMapping("film-ticketing-second-step")
    public String filmTicketingSecondStep(Model model, HttpServletRequest request, HttpServletResponse response) {
        Context ctx = new Context();
        ctx.request = request;
        ctx.response = response;
        ctx.SetUnicodeCookie("current-user", "", "/");

        String[] unavaibleSeat = new String[]{"A1", "B1", "C1", "D1", "C5", "F7", "C7"};
        ctx.SetUnicodeCookie("_arr_unvailable_seats", String.join(".", unavaibleSeat), "/");

        ctx = Midleware.Authenticate(ctx);
		if (!Midleware.IsSignedIn(ctx)) {
			return "redirect:/auth/login";
        }
        return "film-ticketing-second-step";
    }
}
