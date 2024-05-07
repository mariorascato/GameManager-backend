package it.unimol.mobile.gamemanager.model.game;

import it.unimol.mobile.gamemanager.model.Piattaforma;
import it.unimol.mobile.gamemanager.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "Games")
public class Game {
    @DBRef
    private Player player;
    @Id
    private String id;
    private String nome;
    private String sviluppatore;
    private List<Piattaforma> piattaforme;
    @Unwrapped.Nullable
    private String immagineURL;
    private int trofeiTotali;
    private int trofeiOttenuti;
    private int valutazione;
    private int oreDiGioco;

}
