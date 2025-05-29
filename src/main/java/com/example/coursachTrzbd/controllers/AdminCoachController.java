package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Coach;
import com.example.coursachTrzbd.services.CoachService;
import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coaches")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCoachController {

    private final CoachService coachService;
    private final TeamService teamService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("coaches", coachService.getAll());
        return "admin/coaches";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("coach", new Coach());
        model.addAttribute("teams", teamService.getAll());
        return "admin/add-coach";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("coach", coachService.getById(id));
        model.addAttribute("teams", teamService.getAll());
        return "admin/edit-coach";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Coach coach) {
        coachService.save(coach);
        return "redirect:/admin/coaches";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        coachService.delete(id);
        return "redirect:/admin/coaches";
    }
}


