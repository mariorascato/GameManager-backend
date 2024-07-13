package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
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
    @PostMapping("addBirthday/{id}")
    public ResponseEntity<Player> addPlayerBirthday(@PathVariable Long id, @RequestBody String dateString) {
        return this.playerService.addOrUpdateBirthday(id, dateString);
    }
    @PutMapping("updateBirthday/{id}")
    public ResponseEntity<Player> updatePlayerBirthday(@PathVariable Long id, @RequestBody String dateString) {
        return this.playerService.addOrUpdateBirthday(id, dateString);
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
    @PutMapping("setPreferito/{id_game}/{id_player}/{preferito}")
    public ResponseEntity<GamePlayer> addGiocoToPreferiti(@PathVariable Long id_game,@PathVariable Long id_player,@PathVariable boolean preferito){
        return this.playerService.setPreferito(id_player,id_game,preferito);
    }
    @PostMapping("addPreferito/{id_player}/{id_game}")
    public ResponseEntity<Player> addGiocoPreferito(@PathVariable Long id_player, @RequestBody Game game, @PathVariable Long id_game) {
        return this.playerService.addGiocoPreferito(id_player, game, id_game);
    }
    @DeleteMapping("removePreferito/{id}")
    public ResponseEntity<Player> removeGiocoPreferito(@PathVariable Long id) {
        return this.playerService.removeGiocoPreferito(id);
    }
}
