package it.unimol.mobile.gamemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("api/game-manager/refresh")
public class RefreshController {
    @GetMapping("do")
    public ResponseEntity<String> refreshWebService() {
        return ResponseEntity.status(HttpStatus.OK).body("Il web service è stato refreshato correttamente");
    }
}
