package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    public ResponseEntity<Player> addPlayer(Player player ){
        if(playerRepository.findByEmail(player.getEmail()).isPresent()){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        playerRepository.save(player);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
    public ResponseEntity<List<Player>> getAllPlayers(){
        if(this.playerRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerRepository.findAll());
    }
    public ResponseEntity<Player> updatePlayer(Player player,String id) {
        if (!playerRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Player playerToUpdate = playerRepository.findById(id).get();

            playerToUpdate.setPassword(player.getPassword());
            playerToUpdate.setEmail(player.getEmail());
            playerToUpdate.setSesso(player.getSesso());
            playerToUpdate.setUsername(player.getUsername());
            playerToUpdate.setPiattaformaPreferita(player.getPiattaformaPreferita());
            playerToUpdate.setGiochiPosseduti(player.getGiochiPosseduti());
            playerToUpdate.setGiochiPreferiti(player.getGiochiPreferiti());

            this.playerRepository.save(playerToUpdate);


            return ResponseEntity.status(HttpStatus.OK).body(playerToUpdate);
        }
    }


}
