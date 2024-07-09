package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
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
public class GamePlayerService {

    private final Game_PlayerRepository gamePlayerRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public ResponseEntity<GamePlayer> addGame(Long id_game, Long id_player, GamePlayer gamePlayer){
        if(playerRepository.findById(id_player).isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (gameRepository.findById(id_game).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Player player = playerRepository.findById(id_player).get();
        Game game = gameRepository.findById(id_game).get();

        List<GamePlayer> giochiPosseduti = player.getGiochiPosseduti();
        for (GamePlayer gp:giochiPosseduti) {
            if(gp.getGame().getId().equals(game.getId())){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }

        gamePlayer.setPlayer(player);
        gamePlayer.setGame(game);

        gamePlayerRepository.save(gamePlayer);

        return ResponseEntity.status(HttpStatus.OK).body(gamePlayer);
    }
    public ResponseEntity<GamePlayer> updateGame(GamePlayer gamePlayer, Long id){
        if (!gamePlayerRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            GamePlayer gamePlayerToUpdate = gamePlayerRepository.findById(id).get();

            gamePlayerToUpdate.setImmagini(gamePlayer.getImmagini());
            gamePlayerToUpdate.setValutazione(gamePlayer.getValutazione());
            gamePlayerToUpdate.setOreDiGioco(gamePlayer.getOreDiGioco());
            gamePlayerToUpdate.setTrofeiOttenuti(gamePlayer.getTrofeiOttenuti());
            gamePlayerToUpdate.setLuogoCompletamento(gamePlayer.getLuogoCompletamento());
            gamePlayerToUpdate.setDataCompletamento(gamePlayer.getDataCompletamento());

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
    public ResponseEntity<List<GamePlayer>> getAll(){
        if(gamePlayerRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gamePlayerRepository.findAll());
    }
    public ResponseEntity<GamePlayer> deleteGamePlayer(Long id){
        if(gamePlayerRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        gamePlayerRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}

