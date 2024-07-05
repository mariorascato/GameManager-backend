package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("api/game-manager/game")
public class GameController {
    private final GameService gameService;
    @PostMapping("addGame")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        return gameService.addGame(game);
    }
    @PostMapping("addGames")
    public ResponseEntity<List<Game>> addGames(@RequestBody List<Game> games){
        return gameService.addGames(games);
    }
    @PutMapping("updateGame/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game,@PathVariable Long id){
        return gameService.updateGame(game,id);
    }
    @DeleteMapping("deleteGame/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable Long id){
        return gameService.deleteGame(id);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<Game>> getAll(){
        return gameService.getAll();
    }



}
