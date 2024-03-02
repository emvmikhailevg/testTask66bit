package urfu.emelianov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urfu.emelianov.entity.Player;

/**
 *  * Интерфейс {@code PlayerRepository} предоставляет методы для доступа и управления сущностями
 *  * игроков в репозитории.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {}