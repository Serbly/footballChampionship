package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Match;
import com.example.coursachTrzbd.services.MatchService;
import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/matches")
@PreAuthorize("hasRole('ADMIN')")
public class AdminMatchController {

    private final MatchService matchService;
    private final TeamService teamService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("matches", matchService.getAll());
        return "admin/matches";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("teams", teamService.getAll());
        return "admin/add-match";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("match", matchService.getById(id));
        model.addAttribute("teams", teamService.getAll());
        return "admin/edit-match";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Match match) {
        matchService.save(match);
        return "redirect:/admin/matches";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        matchService.delete(id);
        return "redirect:/admin/matches";
    }
}
