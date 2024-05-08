package it.unimol.mobile.gamemanager.controller;

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
}
