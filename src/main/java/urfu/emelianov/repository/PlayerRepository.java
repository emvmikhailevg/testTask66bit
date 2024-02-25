package urfu.emelianov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import urfu.emelianov.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {}