package urfu.emelianov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import urfu.emelianov.entity.Player;
import urfu.emelianov.repository.PlayerRepository;
import urfu.emelianov.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/list")
    public String getPlayersListPage(Model model) {
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "players_list";
    }

    @GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable Long id, Model model) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            model.addAttribute("player", player);
            model.addAttribute("teams", teamRepository.findAll());
            return "edit_player";
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/edit")
    public String updatePlayer(@ModelAttribute Player updatedPlayer) {
        Optional<Player> player = playerRepository.findById(updatedPlayer.getId());
        if (player.isPresent()) {
            Player currentPlayer = player.get();
            currentPlayer.setName(updatedPlayer.getName());
            currentPlayer.setSurname(updatedPlayer.getSurname());
            currentPlayer.setBirthday(updatedPlayer.getBirthday());
            currentPlayer.setSex(updatedPlayer.getSex());
            currentPlayer.setTeam(updatedPlayer.getTeam());
            currentPlayer.setCountry(updatedPlayer.getCountry());
            playerRepository.save(currentPlayer);
        }
        return "redirect:/list";
    }
}
