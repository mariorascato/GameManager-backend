package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PlayerRepository extends JpaRepository<Player,Long> {
    public Optional<Player> findByEmail(String email);


}
