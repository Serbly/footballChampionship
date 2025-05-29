package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Standing;
import com.example.coursachTrzbd.services.ChampionshipService;
import com.example.coursachTrzbd.services.StandingService;
import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/standings")
@PreAuthorize("hasRole('ADMIN')")
public class AdminStandingController {

    private final StandingService standingService;
    private final ChampionshipService championshipService;
    private final TeamService teamService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("standings", standingService.getAll());
        return "admin/standings";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("standing", new Standing());
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("championships", championshipService.getAll());
        return "admin/add-standing";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Standing standing) {
        standingService.save(standing);
        return "redirect:/admin/standings";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        standingService.delete(id);
        return "redirect:/admin/standings";
    }
}
