package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Coach;
import com.example.coursachTrzbd.services.CoachService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/coaches")
public class AdminCoachController {
    public AdminCoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    private final CoachService coachService;

    @GetMapping
    public String listCoaches(Model model) {
        model.addAttribute("coaches", coachService.getAll());
        return "admin/coaches";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("coach", new Coach());
        return "admin/coaches/add";
    }

    @PostMapping("/add")
    public String addCoach(@Valid @ModelAttribute("coach") Coach coach,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/coaches/add";
        }
        coachService.save(coach);
        return "redirect:/admin/coaches";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Coach coach = coachService.getById(id);
        model.addAttribute("coach", coach);
        return "admin/coaches/edit";
    }

    @PostMapping("/edit")
    public String updateCoach(@Valid @ModelAttribute("coach") Coach coach,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/coaches/edit";
        }
        coachService.save(coach);
        return "redirect:/admin/coaches";
    }

    @PostMapping("/delete/{id}")
    public String deleteCoach(@PathVariable Integer id) {
        coachService.delete(id);
        return "redirect:/admin/coaches";
    }
}
