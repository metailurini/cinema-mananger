package com.j05promax.cinema.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPage {
    @GetMapping("/main-page")
    public String mainpage(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("staffName", "Staff's name");

        ArrayList<Integer> carouselLoop = new ArrayList<Integer>();
        carouselLoop.add(0);
        carouselLoop.add(1);
        carouselLoop.add(2);
        model.addAttribute("carouselLoop", carouselLoop);

        ArrayList<carouselLoopData> carouselLoopData = new ArrayList<>();
        carouselLoopData.add(new carouselLoopData(0, "GODZILLA:KING OF THE MONSTER",
                "Phiêu lưu - Hành động, Khoa học - Viễn tưởng", 132,
                "https://source.unsplash.com/RCAhiGJsUUE/1920x1080"));
        carouselLoopData.add(new carouselLoopData(0, "GODZILLA:KING OF THE MONSTER",
                "Phiêu lưu - Hành động, Khoa học - Viễn tưởng", 132,
                "https://source.unsplash.com/wfh8dDlNFOk/1920x1080"));
        carouselLoopData.add(new carouselLoopData(0, "GODZILLA:KING OF THE MONSTER",
                "Phiêu lưu - Hành động, Khoa học - Viễn tưởng", 132,
                "https://source.unsplash.com/lHGeqh3XhRY/1920x1080"));

        model.addAttribute("carouselLoopData", carouselLoopData);
        return "main-page";
    }
}

class carouselLoopData {
    Integer id;
    String filmName;
    String category;
    Integer time;
    String backgroundImg;

    carouselLoopData(Integer id,
            String filmName,
            String category,
            Integer time,
            String backgroundImg) {
        this.id = id;
        this.filmName = filmName;
        this.category = category;
        this.time = time;
        this.backgroundImg = backgroundImg;
    }
}