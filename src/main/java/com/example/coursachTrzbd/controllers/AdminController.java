package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final TeamService teamService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "admin/dashboard";
    }
}
