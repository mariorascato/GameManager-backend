package it.unimol.mobile.gamemanager.controller;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Player> addPlayerBirthday(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return this.playerService.addOrUpdateBirthday(id, request.get("dateString"));
    }
    @PutMapping("updateBirthday/{id}")
    public ResponseEntity<Player> updatePlayerBirthday(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return this.playerService.addOrUpdateBirthday(id, request.get("dateString"));
    }
    @PostMapping("addGenere/{id}")
    public ResponseEntity<Player> addPlayerGenere(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return this.playerService.addOrUpdateGenere(id, request.get("genere"));
    }
    @PutMapping("updateGenere/{id}")
    public ResponseEntity<Player> updatePlayerGenere(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return this.playerService.addOrUpdateGenere(id, request.get("genere"));
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
}
