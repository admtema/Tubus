package com.admolodtsov.Tubus.controllers;


import com.admolodtsov.Tubus.entities.DesignProject;
import com.admolodtsov.Tubus.services.DesignProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DesignProjectController {

    @Autowired
    private DesignProjectService designProjectService;

    @GetMapping("/all_projects")
    public String showAllProjects(Model model) {
        model.addAttribute("username", MainController.getCurrentUsername());
        List<DesignProject> allDesignProjects = designProjectService.getAllDesignProjects();
        model.addAttribute("allDesignProjects", allDesignProjects);
        return "all-projects-view";
    }


}
