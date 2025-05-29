package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Goal;
import com.example.coursachTrzbd.services.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/goals")
@RequiredArgsConstructor
public class AdminGoalController {

    private final GoalService goalService;

    @GetMapping
    public String listGoals(Model model) {
        model.addAttribute("goals", goalService.getAll());
        return "admin/goals";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("goal", new Goal());
        return "admin/goals/add";
    }

    @PostMapping("/add")
    public String addGoal(@Valid @ModelAttribute("goal") Goal goal,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/goals/add";
        }
        goalService.save(goal);
        return "redirect:/admin/goals";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Goal goal = goalService.getById(id);
        model.addAttribute("goal", goal);
        return "admin/goals/edit";
    }

    @PostMapping("/edit")
    public String updateGoal(@Valid @ModelAttribute("goal") Goal goal,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/goals/edit";
        }
        goalService.save(goal);
        return "redirect:/admin/goals";
    }

    @PostMapping("/delete/{id}")
    public String deleteGoal(@PathVariable Integer id) {
        goalService.delete(id);
        return "redirect:/admin/goals";
    }
}
