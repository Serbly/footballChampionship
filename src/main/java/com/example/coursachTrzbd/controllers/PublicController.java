package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublicController {
    private final MatchService matchService;
    private final StandingService standingService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final ChampionshipService championshipService;
    private final CoachService coachService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/matches")
    public String viewMatches(Model model) {
        model.addAttribute("matches", matchService.getAll());
        return "matches";
    }

    @GetMapping("/standings")
    public String viewStandings(Model model) {
        model.addAttribute("standings", standingService.getAll());
        return "standings";
    }

    @GetMapping("/teams")
    public String viewTeams(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "teams";
    }

    @GetMapping("/players")
    public String viewPlayers(Model model) {
        model.addAttribute("players", playerService.getAll());
        return "players";
    }

    @GetMapping("/championships")
    public String viewChampionships(Model model) {
        model.addAttribute("championships", championshipService.getAll());
        return "championships";
    }

    @GetMapping("/coaches")
    public String viewCoaches(Model model) {
        model.addAttribute("coaches", coachService.getAll());
        return "coaches";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
