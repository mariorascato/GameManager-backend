package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.repository.GameRepository;
import it.unimol.mobile.gamemanager.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


}
