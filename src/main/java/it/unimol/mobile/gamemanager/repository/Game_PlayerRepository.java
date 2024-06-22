package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Game_PlayerRepository extends JpaRepository<Game_Player,Long> {

    public Optional<List<Game_Player>> getGame_PlayersByValutazioneBetween(int val1, int val2);




}
