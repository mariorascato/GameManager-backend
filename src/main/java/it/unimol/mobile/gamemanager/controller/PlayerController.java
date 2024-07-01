package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/game-manager/player")

public class PlayerController {
    private final PlayerService playerService;
    @PostMapping("addPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        return this.playerService.addPlayer(player);
    }
    @GetMapping("getAll")
    public ResponseEntity<List<Player>> getAllPlayers(){
        return this.playerService.getAllPlayers();
    }
    @PutMapping("updatePlayer/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player,@PathVariable Long id){
        return this.playerService.updatePlayer(player,id);
    }
    @DeleteMapping("deletePlayer/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        return this.playerService.deletePlayer(id);
    }
    @GetMapping("getPlayerByEmail/{email}")
    public ResponseEntity<Player> getPlayerByEmail(@PathVariable String email){
        return this.playerService.findPlayerByEmail(email);
    }

    @GetMapping("getGiochiPosseduti/{id}")
    public ResponseEntity<List<GamePlayer>> getGiochiPosseduti(@PathVariable Long id){
        return this.playerService.getAllGiochiPosseduti(id);
    }
    @GetMapping("getGiochiPreferiti/{id}")
    public ResponseEntity<List<GamePlayer>> getGiochiPreferiti(@PathVariable Long id){
        return this.playerService.getAllGiochiPreferiti(id);
    }
    @PutMapping("addGiocoToPreferiti/{id_game}/{id_player}")
    public ResponseEntity<GamePlayer> addGiocoToPreferiti(@PathVariable Long id_game,@PathVariable Long id_player){
        return this.playerService.addGiocoToPreferiti(id_player,id_game);
    }

}
