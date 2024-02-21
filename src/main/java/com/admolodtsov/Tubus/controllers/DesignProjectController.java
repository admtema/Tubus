package com.admolodtsov.Tubus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DesignProjectController {

    @GetMapping("/projects")
    public String showAllDocuments(Model model){
        return "projects-view";
    }
}
