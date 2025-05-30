package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Match;
import com.example.coursachTrzbd.services.ChampionshipService;
import com.example.coursachTrzbd.services.MatchService;
import com.example.coursachTrzbd.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/matches")
public class AdminMatchController {

    private final ChampionshipService championshipService;
    private final TeamService teamService;

    public AdminMatchController(MatchService matchService, ChampionshipService championshipService, TeamService teamService) {
        this.matchService = matchService;
        this.championshipService = championshipService;
        this.teamService = teamService;
    }

    private final MatchService matchService;

    @GetMapping
    public String listMatches(Model model) {
        model.addAttribute("matches", matchService.getAll());
        model.addAttribute("championships", championshipService.getAll());
        model.addAttribute("teams", teamService.getAll());
        return "admin/matches";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("championships", championshipService.getAll());
        model.addAttribute("teams", teamService.getAll());
        return "admin/matches/add";
    }

    @PostMapping("/add")
    public String addMatch(@Valid @ModelAttribute("match") Match match,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/matches/add";
        }
        matchService.save(match);
        return "redirect:/admin/matches";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Match match = matchService.getById(id);
        model.addAttribute("match", match);
        model.addAttribute("championships", championshipService.getAll());
        model.addAttribute("teams", teamService.getAll());
        return "admin/matches/edit";
    }

    @PostMapping("/edit")
    public String updateMatch(@Valid @ModelAttribute("match") Match match,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/matches/edit";
        }
        matchService.save(match);
        return "redirect:/admin/matches";
    }

    @PostMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Integer id) {
        matchService.delete(id);
        return "redirect:/admin/matches";
    }
}
