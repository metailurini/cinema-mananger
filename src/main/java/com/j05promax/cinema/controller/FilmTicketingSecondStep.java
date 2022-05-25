package com.j05promax.cinema.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.j05promax.cinema.repo.PostgreSQLRepo;

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

        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();
        ArrayList<String> unavaibleSeat = repo.Ticket.getSeats();
        System.out.println(String.join(".", unavaibleSeat));
        ctx.SetUnicodeCookie("_arr_unvailable_seats", String.join(".", unavaibleSeat), "/");
        ctx.SetUnicodeCookie("_arr_seats", "[]", "/");

        ctx = Midleware.Authenticate(ctx);
		if (!Midleware.IsSignedIn(ctx)) {
			return "redirect:/auth/login";
        }
        return "film-ticketing-second-step";
    }
}
