package it.unimol.mobile.gamemanager.model.player;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.game_player.Game_Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

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
    private List<Game_Player> giochiPosseduti;

    @OneToMany(mappedBy = "player",cascade = CascadeType.ALL)
    private List<Game_Player> giochiPreferiti;


}
