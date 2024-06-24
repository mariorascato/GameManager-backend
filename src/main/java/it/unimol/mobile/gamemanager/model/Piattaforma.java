package it.unimol.mobile.gamemanager.model;

import it.unimol.mobile.gamemanager.model.game.Game;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;



public enum Piattaforma {
    PS5,
    PS4,
    XBOX_SERIES_S,
    XBOX_SERIES_X,
    NINTENDO_SWITCH,
    PC
    ;

}
