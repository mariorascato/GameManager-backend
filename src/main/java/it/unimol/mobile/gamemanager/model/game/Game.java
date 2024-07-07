package it.unimol.mobile.gamemanager.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String sviluppatore;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> piattaforme;

    private String  immagineURL;
    private boolean isNetworkImage;
    private int trofeiTotali;

    @JsonIgnore
    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL)
    private List<GamePlayer> gamePlayers;
}
