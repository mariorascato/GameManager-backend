package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.Game;
import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    public Optional<List<Game>> getGame_PlayersByPiattaformeContaining(Piattaforma piattaforma);
}
