package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/gamemanager/game")
public class GameController {
    private final GameService gameService;
    @PostMapping("addGame/{id_player}")
    public ResponseEntity<Game> addGame(@RequestBody Game game, @PathVariable String id_player){
        return this.gameService.addGame(game,id_player);
    }
    @PutMapping("updateGame/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable String id){
        return this.gameService.updateGame(game,id);
    }
    @GetMapping("getPlayerByGame/{id}")
    public ResponseEntity<Player> getPlayerByGame(@PathVariable String id){
        return this.gameService.getPlayerByGame(id);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<Game>> getAll(){
        return this.gameService.getAll();
    }
    @DeleteMapping("deleteGame/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable String id){
        return this.gameService.deleteGame(id);
    }
}
