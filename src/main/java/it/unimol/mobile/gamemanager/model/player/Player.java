package it.unimol.mobile.gamemanager.model.player;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game.Game;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Players")
public class Player {
    public Player (){
        giochiPosseduti = new ArrayList<>();
        giochiPreferiti = new ArrayList<>();
    }
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private Sesso sesso;
    private LocalDate birthday;
    private Piattaforma piattaformaPreferita;
    @DBRef
    @Indexed(unique = true)
    private List<Game> giochiPosseduti;
    @DBRef
    @Indexed(unique = true)
    private List<Game> giochiPreferiti;


}
