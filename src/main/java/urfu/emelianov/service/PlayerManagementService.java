package urfu.emelianov.service;

import urfu.emelianov.entity.Player;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для базовых операций с игроками
 */
public interface PlayerManagementService {

    /**
     * Получает список всех игроков.
     * @return Список всех игроков.
     */
    List<Player> getAllPlayers();

    /**
     * Получает игрока по его идентификатору.
     * @param id Идентификатор игрока.
     * @return Объект игрока или null, если игрок не найден.
     */
    Player getPlayerById(Long id);

    /**
     * Обновляет информацию об игроке.
     * @param updatedPlayer Обновленные данные игрока.
     */
    void updatePlayer(Player updatedPlayer);
}
