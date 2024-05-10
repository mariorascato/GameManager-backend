package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.repository.GameRepository;
import it.unimol.mobile.gamemanager.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public ResponseEntity<Game> addGame(Game game,String id_player){
        if(playerRepository.findById(id_player).isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (gameRepository.getGameByNome(game.getNome()).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Player player = playerRepository.findById(id_player).get();
        game.setPlayer(player);
        Game game1 = gameRepository.save(game);

        player.getGiochiPosseduti().add(game1);
        playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
    public ResponseEntity<Game> updateGame(Game game, String id){
        if (!gameRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Game gameToUpdate = gameRepository.findById(id).get();

            gameToUpdate.setPlayer(game.getPlayer());
            gameToUpdate.setNome(game.getNome());
            gameToUpdate.setPiattaforme(game.getPiattaforme());
            gameToUpdate.setSviluppatore(game.getSviluppatore());
            gameToUpdate.setImmagineURL(game.getImmagineURL());
            gameToUpdate.setValutazione(game.getValutazione());
            gameToUpdate.setOreDiGioco(game.getOreDiGioco());
            gameToUpdate.setTrofeiOttenuti(game.getTrofeiOttenuti());
            gameToUpdate.setTrofeiTotali(game.getTrofeiTotali());

            this.gameRepository.save(gameToUpdate);


            return ResponseEntity.status(HttpStatus.OK).body(gameToUpdate);
        }
    }
    public ResponseEntity<Player> getPlayerByGame(String id_game) {
        if (!gameRepository.findById(id_game).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (gameRepository.findById(id_game).get().getPlayer() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findById(id_game).get().getPlayer());
    }
    public ResponseEntity<List<Game>> getAll(){
        if(gameRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
    }
    public ResponseEntity<Game> deleteGame(String id){
        if(gameRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        gameRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    public ResponseEntity<List<Game>> getGamesBySviluppatore(String sviluppatore){
        if(gameRepository.getGamesBySviluppatore(sviluppatore).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.getGamesBySviluppatore(sviluppatore).get());
    }
    public ResponseEntity<List<Game>> getGamesByPiattaforma(Piattaforma piattaforma){
        if(gameRepository.getGamesByPiattaformeContaining(piattaforma).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.getGamesByPiattaformeContaining(piattaforma).get());
    }

    public ResponseEntity<List<Game>> getGamesByValutazioneBetween(int val1, int val2){
        if(gameRepository.getGamesByValutazioneBetween(val1,val2).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.getGamesByValutazioneBetween(val1,val2).get());
    }

}

