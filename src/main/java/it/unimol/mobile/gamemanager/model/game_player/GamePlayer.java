package it.unimol.mobile.gamemanager.model.game_player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.player.Player;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String immagineURL;
    private int trofeiTotali;
    private int trofeiOttenuti;
    private int valutazione;
    private int oreDiGioco;
    private String luogoCompletamento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    private Boolean preferito;


}
