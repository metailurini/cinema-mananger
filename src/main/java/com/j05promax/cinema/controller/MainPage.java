package com.j05promax.cinema.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.j05promax.cinema.config.Config;
import com.j05promax.cinema.entity.Film;

@Controller
public class MainPage {
    @GetMapping("/main-page")
    public String mainpage(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("staffName", "Staff's name");

        Config c = Config.getInstance();

        try {
            List<Film> films = c.filmRepo.GetAll();
            model.addAttribute("films", films);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "main-page";
    }
}