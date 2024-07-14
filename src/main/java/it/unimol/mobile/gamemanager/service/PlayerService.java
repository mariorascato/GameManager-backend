package it.unimol.mobile.gamemanager.service;

import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import it.unimol.mobile.gamemanager.model.player.Genere;
import it.unimol.mobile.gamemanager.model.player.Player;
import it.unimol.mobile.gamemanager.repository.GameRepository;
import it.unimol.mobile.gamemanager.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public ResponseEntity<Player> addPlayer(Player player ){
        if(playerRepository.findByEmail(player.getEmail()).isPresent()){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        playerRepository.save(player);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
    public ResponseEntity<Player> findPlayerByEmail(String email){
        if(playerRepository.findByEmail(email).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(playerRepository.findByEmail(email).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    public ResponseEntity<List<Player>> getAllPlayers(){
        if(this.playerRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerRepository.findAll());
    }

    public ResponseEntity<Player> addOrUpdateBirthday(Long id, String dateString) {
        if (playerRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Player playerToEdit = playerRepository.findById(id).get();

            try {
                LocalDate birthday = LocalDate.parse(dateString);

                playerToEdit.setBirthday(birthday);

                playerRepository.save(playerToEdit);

                return ResponseEntity.status(HttpStatus.OK).body(playerToEdit);

            } catch (DateTimeParseException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
    }

    public ResponseEntity<Player> addOrUpdateGenere(Long id, String genere) {
        if (playerRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Player playerToEdit = playerRepository.findById(id).get();
            Genere genereToAdd = switch (genere) {
                case "MASCHIO" -> Genere.MASCHIO;
                case "FEMMINA" -> Genere.FEMMINA;
                case "NON BINARIO" -> Genere.NON_BINARIO;
                default -> null;
            };

            playerToEdit.setGenere(genereToAdd);
            playerRepository.save(playerToEdit);

            return ResponseEntity.status(HttpStatus.OK).body(playerToEdit);
        }
    }

    public ResponseEntity<Player> updatePlayer(Player player,Long id) {
        if (!playerRepository.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Player playerToUpdate = playerRepository.findById(id).get();

            playerToUpdate.setUsername(player.getUsername());
            playerToUpdate.setEmail(player.getEmail());
            playerToUpdate.setPassword(player.getPassword());
            playerToUpdate.setPiattaformaPreferita(player.getPiattaformaPreferita());
            playerToUpdate.setGiochiPosseduti(player.getGiochiPosseduti());

            this.playerRepository.save(playerToUpdate);


            return ResponseEntity.status(HttpStatus.OK).body(playerToUpdate);
        }
    }
    public ResponseEntity<Player> deletePlayer(Long id){
        if(this.playerRepository.findById(id).isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.playerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    public ResponseEntity<List<GamePlayer>> getAllGiochiPosseduti(Long id){
        if(this.playerRepository.findById(id).isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.playerRepository.findById(id).get().getGiochiPosseduti());
    }
    public ResponseEntity<List<GamePlayer>> getAllGiochiPreferiti(Long id){
        if(this.playerRepository.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Player player = playerRepository.findById(id).get();
        List<GamePlayer> giochiPreferiti = new ArrayList<>();
        for (GamePlayer gp:player.getGiochiPosseduti()) {
            if(gp.getPreferito()){
                giochiPreferiti.add(gp);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(giochiPreferiti);
    }

    public ResponseEntity<GamePlayer> setPreferito(Long id_player, Long id_game, boolean preferito){
        if(this.playerRepository.findById(id_player).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Player player = this.playerRepository.findById(id_player).get();
        for (GamePlayer gp:player.getGiochiPosseduti()) {
            if(gp.getId().equals(id_game)){
                gp.setPreferito(preferito);
                playerRepository.save(player);

                return ResponseEntity.status(HttpStatus.OK).body(gp);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Player> addGiocoPreferito(Long id_player, Game game, Long id_game) {
        if (playerRepository.findById(id_player).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (gameRepository.findById(id_game).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Player player = playerRepository.findById(id_player).get();

        player.setGiocoPreferito(game);
        playerRepository.save(player);

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }

    public ResponseEntity<Player> removeGiocoPreferito(Long id) {
        if (playerRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Player playerToUpdate = playerRepository.findById(id).get();
            playerToUpdate.setGiocoPreferito(null);
            playerRepository.save(playerToUpdate);

            return ResponseEntity.status(HttpStatus.OK).body(playerToUpdate);
        }
    }


}
