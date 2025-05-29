package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Team;
import com.example.coursachTrzbd.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/teams")
@RequiredArgsConstructor
public class AdminTeamController {

    private final TeamService teamService;

    @GetMapping
    public String listTeams(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "admin/teams";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("team", new Team());
        return "admin/teams/add";
    }

    @PostMapping("/add")
    public String addTeam(@Valid @ModelAttribute("team") Team team,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/teams/add";
        }
        teamService.save(team);
        return "redirect:/admin/teams";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Team team = teamService.getById(id);
        model.addAttribute("team", team);
        return "admin/teams/edit";
    }

    @PostMapping("/edit")
    public String updateTeam(@Valid @ModelAttribute("team") Team team,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/teams/edit";
        }
        teamService.save(team);
        return "redirect:/admin/teams";
    }

    @PostMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Integer id) {
        teamService.delete(id);
        return "redirect:/admin/teams";
    }
}
