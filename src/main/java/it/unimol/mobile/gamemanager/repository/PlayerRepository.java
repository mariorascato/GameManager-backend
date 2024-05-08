package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PlayerRepository extends MongoRepository<Player,String> {
    public Optional<Player> findByEmail(String email);


}
