package it.unimol.mobile.gamemanager.model.game_player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.Game;
import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.player.Player;
import jakarta.persistence.*;
import lombok.Data;



import java.util.List;

@Data
@Entity
public class Game_Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String immagineURL;
    private int trofeiTotali;
    private int trofeiOttenuti;
    private int valutazione;
    private int oreDiGioco;

    @JsonIgnore
    @ManyToOne
    private Player player;
    @JsonIgnore
    @ManyToOne
    private Game game;


}
