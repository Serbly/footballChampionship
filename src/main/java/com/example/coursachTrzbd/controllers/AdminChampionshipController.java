package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Championship;
import com.example.coursachTrzbd.services.ChampionshipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/championships")
public class AdminChampionshipController {

    public AdminChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    private final ChampionshipService championshipService;

    @GetMapping
    public String listChampionships(Model model) {
        model.addAttribute("championships", championshipService.getAll());
        return "admin/championships";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("championship", new Championship());
        return "admin/championships/add";
    }

    @PostMapping("/add")
    public String addChampionship(@Valid @ModelAttribute("championship") Championship championship,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/championships/add";
        }
        championshipService.save(championship);
        return "redirect:/admin/championships";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Championship championship = championshipService.getById(id);
        model.addAttribute("championship", championship);
        return "admin/championships/edit";
    }

    @PostMapping("/edit")
    public String updateChampionship(@Valid @ModelAttribute("championship") Championship championship,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/championships/edit";
        }
        championshipService.save(championship);
        return "redirect:/admin/championships";
    }

    @PostMapping("/delete/{id}")
    public String deleteChampionship(@PathVariable Integer id) {
        championshipService.delete(id);
        return "redirect:/admin/championships";
    }
}
