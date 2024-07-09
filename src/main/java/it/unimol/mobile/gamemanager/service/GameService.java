package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public ResponseEntity<Game> addGame(Game game){
        if(gameRepository.findGameByNome(game.getNome()).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.save(game));

    }
    public ResponseEntity<Game> updateGame(Game game, Long id){
        if (gameRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Game gameToUpdate = gameRepository.findById(id).get();

            gameToUpdate.setNome(game.getNome());
            gameToUpdate.setPiattaforme(game.getPiattaforme());
            gameToUpdate.setSviluppatore(game.getSviluppatore());
            gameToUpdate.setTrofeiTotali(game.getTrofeiTotali());
            gameToUpdate.setNetworkImage(game.isNetworkImage());

            this.gameRepository.save(gameToUpdate);


            return ResponseEntity.status(HttpStatus.OK).body(gameToUpdate);
        }
    }
    public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
    }
    public ResponseEntity<Game> deleteGame(Long id){
        if(gameRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Game game = gameRepository.findById(id).get();

        gameRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
    public ResponseEntity<List<Game>> addGames(List<Game> games){
        if(games.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else return ResponseEntity.status(HttpStatus.OK).body(gameRepository.saveAll(games));
    }

}
