package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/gamemanager/game")
public class GameController {
    private final GameService gameService;
    @PostMapping("addGame/{id_player}")
    public ResponseEntity<Game> addGame(@RequestBody Game game, @PathVariable String id_player){
        return this.gameService.addGame(game,id_player);
    }
}
