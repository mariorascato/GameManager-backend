package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.GamePlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/game-manager/gamePlayer")
public class GamePlayerController {
    private final GamePlayerService gamePlayerService;
    @PostMapping("addGameToPlayer/{id_game}/{id_player}")
    public ResponseEntity<GamePlayer> addGame(@PathVariable Long id_game, @PathVariable Long id_player, @RequestBody GamePlayer gamePlayer){
        return this.gamePlayerService.addGame(id_game,id_player,gamePlayer);
    }
    @PutMapping("updateGamePlayer/{id}")
    public ResponseEntity<GamePlayer> updateGame(@RequestBody GamePlayer gamePlayer, @PathVariable Long id){
        return this.gamePlayerService.updateGame(gamePlayer,id);
    }
    @GetMapping("getPlayerByGamePlayer/{id}")
    public ResponseEntity<Player> getPlayerByGame(@PathVariable Long id){
        return this.gamePlayerService.getPlayerByGamePlayer(id);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<GamePlayer>> getAll(){
        return this.gamePlayerService.getAll();
    }
    @DeleteMapping("deleteGamePlayer/{id}")
    public ResponseEntity<GamePlayer> deleteGame(@PathVariable Long id){
        return this.gamePlayerService.deleteGamePlayer(id);
    }

}
