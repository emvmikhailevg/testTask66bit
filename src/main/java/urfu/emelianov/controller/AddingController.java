package urfu.emelianov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import urfu.emelianov.entity.Player;
import urfu.emelianov.entity.Team;
import urfu.emelianov.repository.PlayerRepository;
import urfu.emelianov.repository.TeamRepository;

@Controller
public class AddingController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public AddingController(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        return "index";
    }

    @PostMapping("/main")
    public String addPlayer(@ModelAttribute Player player, @RequestParam(required = false) String newTeamName) {
        Team team = null;
        if (newTeamName != null && !newTeamName.isEmpty()) {
            Team newTeam = new Team();
            newTeam.setName(newTeamName);
            teamRepository.save(newTeam);
            team = newTeam;
        } else if (player.getTeam() != null && player.getTeam().getId() != null) {
            team = teamRepository.findById(player.getTeam().getId()).orElse(null);
        }

        if (team != null) {
            player.setTeam(team);
        }

        playerRepository.save(player);

        return "redirect:/main";
    }
}
