package it.unimol.mobile.gamemanager.model.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unimol.mobile.gamemanager.model.game.Game;
import it.unimol.mobile.gamemanager.model.game_player.GamePlayer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String avatar;

    private Genere genere;
    private LocalDate birthday;
    private String piattaformaPreferita;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(unique = true)
    @JsonIgnore()
    private List<GamePlayer> giochiPosseduti;

    private String giocoPreferito;
}
