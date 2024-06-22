package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/gamemanager/player")

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
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player,@PathVariable String id){
        return this.playerService.updatePlayer(player,id);
    }
    @DeleteMapping("deletePlayer/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable String id){
        return this.playerService.deletePlayer(id);
    }
    @GetMapping("getPlayerByEmail/{email}")
    public ResponseEntity<Player> getPlayerByEmail(@PathVariable String email){
        return this.playerService.findPlayerByEmail(email);
    }
    @GetMapping("getGiochiPreferiti/{id}")
    public ResponseEntity<List<Game_Player>> getGiochiPreferiti(@PathVariable String id){
        return this.playerService.getAllGiochiPreferiti(id);
    }
    @GetMapping("getGiochiPosseduti/{id}")
    public ResponseEntity<List<Game_Player>> getGiochiPosseduti(@PathVariable String id){
        return this.playerService.getAllGiochiPosseduti(id);
    }
    @PutMapping("addGiocoPreferito/{id}/{nome}")
    public ResponseEntity<Player> addGiocoPreferito(@PathVariable String id, @PathVariable String nome){
        return this.playerService.addGiocoPreferito(id,nome);
    }
}
