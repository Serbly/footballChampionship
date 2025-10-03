package com.example.coursachTrzbd.controllers;

import com.example.coursachTrzbd.entity.*;
import com.example.coursachTrzbd.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        String selectedSeason = (season != null && !season.isEmpty())
                ? season
                : (availableSeasons.isEmpty() ? null : availableSeasons.get(0));

        Championship selectedChampionship = (selectedSeason != null)
                ? championshipService.getByNameAndSeason(current.getName(), selectedSeason)
                : null;

        List<Standing> standings = (selectedChampionship != null)
                ? standingService.findByChampionship(selectedChampionship)
                : List.of();

        List<Object[]> topScorers = (selectedSeason != null)
                ? goalService.findTopScorersByChampionshipNameAndSeason(current.getName(), selectedSeason)
                : List.of();

        model.addAttribute("championship", current);
        model.addAttribute("seasons", availableSeasons);
        model.addAttribute("selectedSeason", selectedSeason);
        model.addAttribute("standings", standings);
        model.addAttribute("topScorers", topScorers);

        return "standings";
    }

    @GetMapping("/api/standings/{id}")
    @ResponseBody
    public Map<String, Object> standingsData(
            @PathVariable Integer id,
            @RequestParam(name = "season") String season) {

        Championship current = championshipService.getById(id);
        Championship selectedChampionship = championshipService.getByNameAndSeason(current.getName(), season);

        List<Standing> standings = (selectedChampionship == null)
                ? Collections.emptyList()
                : standingService.findByChampionship(selectedChampionship);

        List<Object[]> topScorersRaw = (selectedChampionship == null)
                ? Collections.emptyList()
                : goalService.findTopScorersByChampionshipNameAndSeason(current.getName(), season);

        // Преобразуем Standings в DTO-список (без ленивых связей и циклических ссылок)
        List<Map<String, Object>> standingsDto = standings.stream().map(s -> {
            Map<String, Object> m = new HashMap<>();
            m.put("teamId", s.getTeam() != null ? s.getTeam().getId() : null);
            m.put("teamName", s.getTeam() != null ? s.getTeam().getName() : "");
            m.put("matchesPlayed", s.getMatchesPlayed());
            m.put("wins", s.getWins());
            m.put("draws", s.getDraws());
            m.put("losses", s.getLosses());
            m.put("points", s.getPoints());
            return m;
        }).collect(Collectors.toList());

        // Преобразуем topScorers (Object[] -> map {player, team, goals})
        List<Map<String, Object>> scorersDto = topScorersRaw.stream().map(row -> {
            Map<String, Object> m = new HashMap<>();
            m.put("player", row.length > 0 ? row[0] : null);
            m.put("team", row.length > 1 ? row[1] : null);
            m.put("goals", row.length > 2 ? row[2] : null);
            return m;
        }).collect(Collectors.toList());

        Map<String, Object> resp = new HashMap<>();
        resp.put("standings", standingsDto);
        resp.put("topScorers", scorersDto);
        return resp;
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
