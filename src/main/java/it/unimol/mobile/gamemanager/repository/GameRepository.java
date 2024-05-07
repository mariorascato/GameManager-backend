package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GameRepository extends MongoRepository<Game,String> {
}
