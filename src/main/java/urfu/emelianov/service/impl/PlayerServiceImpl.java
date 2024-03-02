package urfu.emelianov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urfu.emelianov.entity.Player;
import urfu.emelianov.entity.Team;
import urfu.emelianov.repository.PlayerRepository;
import urfu.emelianov.repository.TeamRepository;
import urfu.emelianov.service.PlayerService;

/**
 * Класс, реализующий методы интерфейса PlayerService и отвечающий за операции добавления
 * новых игроков и управление командами при добавлении.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    /**
     * Конструктор класса.
     * @param playerRepository Репозиторий игроков.
     * @param teamRepository Репозиторий команд.
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void addPlayer(Player player, String newTeamName) {
        Team team = null;
        if (newTeamName != null && !newTeamName.isEmpty()) {
            Team newTeam = new Team();
            newTeam.setName(newTeamName);
            team = teamRepository.save(newTeam);
        } else if (player.getTeam().getId() != null) {
            team = teamRepository.findById(player.getTeam().getId()).orElse(null);
        }

        if (team != null) {
            player.setTeam(team);
        }

        playerRepository.save(player);
    }
}
