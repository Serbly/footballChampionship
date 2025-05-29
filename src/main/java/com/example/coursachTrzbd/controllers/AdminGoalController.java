package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Goal;
import com.example.coursachTrzbd.services.GoalService;
import com.example.coursachTrzbd.services.MatchService;
import com.example.coursachTrzbd.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/goals")
@PreAuthorize("hasRole('ADMIN')")
public class AdminGoalController {

    private final GoalService goalService;
    private final MatchService matchService;
    private final PlayerService playerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("goals", goalService.getAll());
        return "admin/goals";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("goal", new Goal());
        model.addAttribute("matches", matchService.getAll());
        model.addAttribute("players", playerService.getAll());
        return "admin/add-goal";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Goal goal) {
        goalService.save(goal);
        return "redirect:/admin/goals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        goalService.delete(id);
        return "redirect:/admin/goals";
    }
}
