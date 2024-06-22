package it.unimol.mobile.gamemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
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
    private List<Piattaforma> piattaforme;

    @JsonIgnore
    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL)
    private List<Game_Player> gamePlayers;
}
