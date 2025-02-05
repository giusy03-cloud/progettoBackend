package com.dipartimento.projecthotelnew.controller;

import com.dipartimento.projecthotelnew.dto.ResponseMessage;
import com.dipartimento.projecthotelnew.model.Recensione;
import com.dipartimento.projecthotelnew.model.User;
import com.dipartimento.projecthotelnew.service.RecensioneService;
import com.dipartimento.projecthotelnew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private UserService userService;  // Servizio per recuperare l'utente

    @PostMapping("/aggiungi")
    public ResponseEntity<ResponseMessage> aggiungiRecensione(@RequestBody Recensione recensione) {
        try {
            // Recupera l'utente in base al nome utente
            User user = userService.findByUsername(recensione.getNomeUtente());

            // Se l'utente esiste, associa l'userId alla recensione
            if (user != null) {
                recensione.setUserId(user.getId()); // Imposta l'userId basato sul nome utente
                recensioneService.addRecensione(recensione); // Salva la recensione
                // Restituisci una risposta JSON di successo
                return ResponseEntity.ok(new ResponseMessage("Recensione aggiunta con successo!"));
            } else {
                // Restituisci una risposta JSON con errore se l'utente non è trovato
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage("Errore: utente non trovato!"));
            }
        } catch (Exception e) {
            // Gestisci gli errori e restituisci una risposta JSON con un messaggio di errore
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Errore nell'aggiunta della recensione: " + e.getMessage()));
        }
    }


    // Ottieni tutte le recensioni
    @GetMapping("/all")
    public ResponseEntity<List<Recensione>> getAllRecensioni() {
        List<Recensione> recensioni = recensioneService.getAllRecensioni();
        return new ResponseEntity<>(recensioni, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteRecensione(@PathVariable int id) {
        try {
            recensioneService.deleteRecensione(id);
            return ResponseEntity.ok(new ResponseMessage("Recensione eliminata con successo"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Errore nel server durante l'eliminazione della recensione"));
        }
    }

}
