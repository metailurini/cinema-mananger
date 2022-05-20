package com.j05promax.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Error {
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
