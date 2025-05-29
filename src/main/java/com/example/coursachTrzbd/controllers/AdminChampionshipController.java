package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Championship;
import com.example.coursachTrzbd.services.ChampionshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/championships")
@PreAuthorize("hasRole('ADMIN')")
public class AdminChampionshipController {

    private final ChampionshipService championshipService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("championships", championshipService.getAll());
        return "admin/championships";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("championship", new Championship());
        return "admin/add-championship";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Championship championship) {
        championshipService.save(championship);
        return "redirect:/admin/championships";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        championshipService.delete(id);
        return "redirect:/admin/championships";
    }
}
