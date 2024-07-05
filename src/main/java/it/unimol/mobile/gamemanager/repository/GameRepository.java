package it.unimol.mobile.gamemanager.repository;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.Piattaforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    public Optional<List<Game>> findGamesByPiattaformeContaining(List<String> piattaforme);
    public Optional<Game> findGameByNome(String nome);
}
