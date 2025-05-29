package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Team;
import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/teams")
@PreAuthorize("hasRole('ADMIN')")
public class AdminTeamController {

    private final TeamService teamService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "admin/teams";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("team", new Team());
        return "admin/add-team";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("team", teamService.getById(id));
        return "admin/edit-team";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/admin/teams";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        teamService.delete(id);
        return "redirect:/admin/teams";
    }
}
