package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.Match;
import com.example.coursachTrzbd.services.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/matches")
@RequiredArgsConstructor
public class AdminMatchController {

    private final MatchService matchService;

    @GetMapping
    public String listMatches(Model model) {
        model.addAttribute("matches", matchService.getAll());
        return "admin/matches";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("match", new Match());
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
