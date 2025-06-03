package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.*;
import com.example.coursachTrzbd.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PublicController {
    public PublicController(MatchService matchService, StandingService standingService, TeamService teamService, PlayerService playerService, ChampionshipService championshipService, GoalService goalService) {
        this.matchService = matchService;
        this.standingService = standingService;
        this.teamService = teamService;
        this.playerService = playerService;
        this.championshipService = championshipService;
        this.goalService = goalService;
    }

    private final MatchService matchService;
    private final StandingService standingService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final ChampionshipService championshipService;
    private final GoalService goalService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("championships", championshipService.getUniqueChampionships());
        return "index";
    }

    @GetMapping("/standings/{id}")
    public String standingsByChampionship(
            @PathVariable Integer id,
            @RequestParam(name = "season", required = false) String season,
            Model model) {

        Championship current = championshipService.getById(id);
        List<String> availableSeasons = championshipService.findSeasonsByChampionshipName(current.getName());
        String selectedSeason = (season != null) ? season : availableSeasons.get(0);

        Championship selectedChampionship = championshipService.getByNameAndSeason(current.getName(), selectedSeason);

        List<Standing> standings = standingService.findByChampionship(selectedChampionship);
        List<Object[]> topScorers = goalService.findTopScorersByChampionshipNameAndSeason(current.getName(), selectedSeason);

        model.addAttribute("championship", selectedChampionship);
        model.addAttribute("seasons", availableSeasons);
        model.addAttribute("selectedSeason", selectedSeason);
        model.addAttribute("standings", standings);
        model.addAttribute("topScorers", topScorers);

        return "standings";
    }

    @GetMapping("/team/{id}")
    public String teamDetails(
            @PathVariable Integer id,
            @RequestParam(name = "season") String season,
            Model model) {

        Team team = teamService.getById(id);
        List<Player> players = playerService.findByTeamId(id);
        List<Match> matches = matchService.findByTeamAndSeason(id, season);

        model.addAttribute("team", team);
        model.addAttribute("season", season);
        model.addAttribute("players", players);
        model.addAttribute("matches", matches);

        return "teams";
    }

    @GetMapping("/match/{id}")
    public String viewMatch(@PathVariable Integer id, Model model) {
        Match match = matchService.getById(id);
        model.addAttribute("match", match);
        return "matches";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
