package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface GameRepository extends MongoRepository<Game,String> {
    public Optional<Game> getGameByNome(String nome);
    public Optional<List<Game>> getGameBySviluppatore(String sviluppatore);


}
