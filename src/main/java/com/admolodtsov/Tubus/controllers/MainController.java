package com.admolodtsov.Tubus.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showMainView(Model model){
        model.addAttribute("title", "Главная страница");
        model.addAttribute("username", getCurrentUsername());
        return "main-view";
    }
    @GetMapping("/about")
    public String showAboutView(Model model){
        model.addAttribute("title", "О проекте");
        model.addAttribute("username", getCurrentUsername());
        return "about-view";
    }

    public static String getCurrentUsername() {
        String authorizedName = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        String currentUserName = "Гость";
        if (!authorizedName.equals("anonymousUser")){
            currentUserName = authorizedName;
        }
        return currentUserName;
    }

}
