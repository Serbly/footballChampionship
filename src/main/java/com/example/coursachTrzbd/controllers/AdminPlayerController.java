package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Player;
import com.example.coursachTrzbd.services.PlayerService;
import com.example.coursachTrzbd.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/players")
public class AdminPlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    public AdminPlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public String listPlayers(Model model) {
        model.addAttribute("players", playerService.getAll());
        return "admin/players";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.getAll());
        return "admin/players/add";
    }

    @PostMapping("/add")
    public String addPlayer(@Valid @ModelAttribute("player") Player player,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/players/add";
        }
        playerService.save(player);
        return "redirect:/admin/players";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Player player = playerService.getById(id);
        model.addAttribute("player", player);
        model.addAttribute("teams", teamService.getAll());
        return "admin/players/edit";
    }

    @PostMapping("/edit")
    public String updatePlayer(@Valid @ModelAttribute("player") Player player,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/players/edit";
        }
        playerService.save(player);
        return "redirect:/admin/players";
    }

    @PostMapping("/delete/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        playerService.delete(id);
        return "redirect:/admin/players";
    }
}
