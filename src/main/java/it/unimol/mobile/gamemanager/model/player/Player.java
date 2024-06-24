package it.unimol.mobile.gamemanager.model.player;

import it.unimol.mobile.gamemanager.model.Piattaforma;
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

    private Sesso sesso;
    private LocalDate birthday;
    private Piattaforma piattaformaPreferita;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Column(unique = true)
    private List<GamePlayer> giochiPosseduti;




}
