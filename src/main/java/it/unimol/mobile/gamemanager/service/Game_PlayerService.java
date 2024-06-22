package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.Game;
import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.repository.GameRepository;
import it.unimol.mobile.gamemanager.repository.Game_PlayerRepository;
import it.unimol.mobile.gamemanager.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Game_PlayerService {

    private final Game_PlayerRepository gamePlayerRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public ResponseEntity<Game_Player> addGame(Long id_game, Long id_player,Game_Player gamePlayer){
        if(playerRepository.findById(id_player).isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (gameRepository.findById(id_game).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Player player = playerRepository.findById(id_player).get();
        Game game = gameRepository.findById(id_game).get();

        gamePlayer.setPlayer(player);
        gamePlayer.setGame(game);

        gamePlayerRepository.save(gamePlayer);

        return ResponseEntity.status(HttpStatus.OK).body(gamePlayer);
    }
    public ResponseEntity<Game_Player> updateGame(Game_Player gamePlayer, Long id){
        if (!gamePlayerRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Game_Player gamePlayerToUpdate = gamePlayerRepository.findById(id).get();

            gamePlayerToUpdate.setImmagineURL(gamePlayer.getImmagineURL());
            gamePlayerToUpdate.setValutazione(gamePlayer.getValutazione());
            gamePlayerToUpdate.setOreDiGioco(gamePlayer.getOreDiGioco());
            gamePlayerToUpdate.setTrofeiOttenuti(gamePlayer.getTrofeiOttenuti());
            gamePlayerToUpdate.setTrofeiTotali(gamePlayer.getTrofeiTotali());

            this.gamePlayerRepository.save(gamePlayerToUpdate);


            return ResponseEntity.status(HttpStatus.OK).body(gamePlayerToUpdate);
        }
    }
    public ResponseEntity<Player> getPlayerByGamePlayer(Long id_game) {
        if (!gamePlayerRepository.findById(id_game).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (gamePlayerRepository.findById(id_game).get().getPlayer() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.findById(id_game).get().getPlayer());
    }
    public ResponseEntity<List<Game_Player>> getAll(){
        if(gamePlayerRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.findAll());
    }
    public ResponseEntity<Game_Player> deleteGamePlayer(Long id){
        if(gamePlayerRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        gamePlayerRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    public ResponseEntity<List<Game_Player>> getGamesBySviluppatore(String sviluppatore){
        if(gamePlayerRepository.getGamesBySviluppatore(sviluppatore).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.getGamesBySviluppatore(sviluppatore).get());
    }
    public ResponseEntity<List<Game_Player>> getGamesByPiattaforma(Piattaforma piattaforma){
        if(gamePlayerRepository.getGame_PlayersByPiattaformeContaining(piattaforma).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.getGame_PlayersByPiattaformeContaining(piattaforma).get());
    }

    public ResponseEntity<List<Game_Player>> getGamesByValutazioneBetween(int val1, int val2){
        if(gamePlayerRepository.getGame_PlayersByValutazioneBetween(val1,val2).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.getGame_PlayersByValutazioneBetween(val1,val2).get());
    }

}

