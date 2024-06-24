package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Game_PlayerRepository extends JpaRepository<GamePlayer,Long> {

    public Optional<List<GamePlayer>> getGame_PlayersByValutazioneBetween(int val1, int val2);




}
