package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.Game_PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/gamemanager/game")
public class GameController {
    private final Game_PlayerService gamePlayerService;
    @PostMapping("addGame/{id_game}/{id_player}")
    public ResponseEntity<Game_Player> addGame(@PathVariable Long id_game, @PathVariable Long id_player,@RequestBody Game_Player gamePlayer){
        return this.gamePlayerService.addGame(id_game,id_player,gamePlayer);
    }
    @PutMapping("updateGame/{id}")
    public ResponseEntity<Game_Player> updateGame(@RequestBody Game_Player gamePlayer, @PathVariable Long id){
        return this.gamePlayerService.updateGame(gamePlayer,id);
    }
    @GetMapping("getPlayerByGame/{id}")
    public ResponseEntity<Player> getPlayerByGame(@PathVariable Long id){
        return this.gamePlayerService.getPlayerByGamePlayer(id);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<Game_Player>> getAll(){
        return this.gamePlayerService.getAll();
    }
    @DeleteMapping("deleteGame/{id}")
    public ResponseEntity<Game_Player> deleteGame(@PathVariable Long id){
        return this.gamePlayerService.deleteGamePlayer(id);
    }
    @GetMapping("getGamesBySviluppatore/{sviluppatore}")
    public ResponseEntity<List<Game_Player>> getGamesBySviluppatore(@PathVariable String sviluppatore){
        return this.gamePlayerService.getGamesBySviluppatore(sviluppatore);
    }
    @GetMapping("getGamesByPiattaforma/{piattaforma}")
    public ResponseEntity<List<Game_Player>> getGamesByPiattaforma(@PathVariable Piattaforma piattaforma){
        return this.gamePlayerService.getGamesByPiattaforma(piattaforma);
    }
    @GetMapping("getGamesByValutazioneBetween/{valore1}/{valore2}")
    public ResponseEntity<List<Game_Player>> getGamesByValutazioneBetween(@PathVariable int valore1, @PathVariable int valore2){
        return this.gamePlayerService.getGamesByValutazioneBetween(valore1,valore2);
    }
}
