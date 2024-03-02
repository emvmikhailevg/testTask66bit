package urfu.emelianov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urfu.emelianov.entity.Player;
import urfu.emelianov.repository.PlayerRepository;
import urfu.emelianov.service.PlayerManagementService;

import java.util.List;

/**
 * Класс, реализующий методы интерфейса PlayerManagementService, предоставляющие базовые операции с игроками
 */
@Service
public class PlayerManagementServiceImpl implements PlayerManagementService {

    private final PlayerRepository playerRepository;

    /**
     * Конструктор класса.
     * @param playerRepository Репозиторий игроков.
     */
    @Autowired
    public PlayerManagementServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePlayer(Player updatedPlayer) {
        playerRepository.save(updatedPlayer);
    }
}
