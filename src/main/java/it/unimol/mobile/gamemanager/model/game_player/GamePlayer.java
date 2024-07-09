package it.unimol.mobile.gamemanager.model.game_player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.player.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> immagini;

    private int trofeiOttenuti;
    private int valutazione;
    private int oreDiGioco;
    private String luogoCompletamento;
    private String dataCompletamento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    private Boolean preferito;


}
