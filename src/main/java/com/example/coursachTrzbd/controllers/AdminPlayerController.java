package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Player;
import com.example.coursachTrzbd.services.PlayerService;
import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/players")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("players", playerService.getAll());
        return "admin/players";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.getAll());
        return "admin/add-player";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("player", playerService.getById(id));
        model.addAttribute("teams", teamService.getAll());
        return "admin/edit-player";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Player player) {
        playerService.save(player);
        return "redirect:/admin/players";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        playerService.delete(id);
        return "redirect:/admin/players";
    }
}
