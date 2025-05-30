package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Standing;
import com.example.coursachTrzbd.services.ChampionshipService;
import com.example.coursachTrzbd.services.StandingService;
import com.example.coursachTrzbd.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/standings")
public class AdminStandingController {

    private final StandingService standingService;
    private final ChampionshipService championshipService;
    private final TeamService teamService;

    public AdminStandingController(StandingService standingService, ChampionshipService championshipService, TeamService teamService) {
        this.standingService = standingService;
        this.championshipService = championshipService;
        this.teamService = teamService;
    }

    @GetMapping
    public String listStandings(Model model) {
        model.addAttribute("standings", standingService.getAll());
        return "admin/standings";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("standing", new Standing());
        model.addAttribute("championships", championshipService.getAll());
        model.addAttribute("teams", teamService.getAll());
        return "admin/standings/add";
    }

    @PostMapping("/add")
    public String addStanding(@Valid @ModelAttribute("standing") Standing standing,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/standings/add";
        }
        standingService.save(standing);
        return "redirect:/admin/standings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Standing standing = standingService.getById(id);
        model.addAttribute("standing", standing);
        model.addAttribute("championships", championshipService.getAll());
        model.addAttribute("teams", teamService.getAll());
        return "admin/standings/edit";
    }

    @PostMapping("/edit")
    public String updateStanding(@Valid @ModelAttribute("standing") Standing standing,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("standings", standingService.getAll());
            return "admin/standings/edit";
        }
        standingService.save(standing);
        return "redirect:/admin/standings";
    }

    @PostMapping("/delete/{id}")
    public String deleteStanding(@PathVariable Integer id) {
        standingService.delete(id);
        return "redirect:/admin/standings";
    }
}
