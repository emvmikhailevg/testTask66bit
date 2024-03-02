package urfu.emelianov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urfu.emelianov.entity.Team;

/**
 * Интерфейс {@code TeamRepository} предоставляет методы для доступа и управления сущностями
 * футбольных команд в репозитории.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {}