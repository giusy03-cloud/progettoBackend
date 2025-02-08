package com.dipartimento.projecthotelnew.controller;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.dto.PrenotazioneResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import com.dipartimento.projecthotelnew.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<List<PrenotazioneDettaglioResponse>> getAllPrenotazioni() {
        //RECUPERA TUTTE LE PRENOTAZIONI CON DETTAGLI AGGIUNTIVI(UTENTE E CAMERA)
        List<PrenotazioneDettaglioResponse> prenotazioni = prenotazioneService.getAllPrenotazioniConDettagli();
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable Integer id) {
        //RECUPERA UNA PRENOTAZIONE TRAMITE ID
        Prenotazione prenotazione = prenotazioneService.getPrenotazioneById(id);
        if (prenotazione == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(prenotazione);
    }

    @PostMapping("/prenota")
    public ResponseEntity<PrenotazioneResponse> savePrenotazione(@RequestBody Prenotazione prenotazione) {
        //EFFETTUA UNA PRENOTAZIONE
        PrenotazioneResponse response = prenotazioneService.savePrenotazione(prenotazione);

        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //DELETE VIENE UTILIZZATA PER ELIMINARE RISORSE DAL SERVER
    @DeleteMapping("/{id}")
    public ResponseEntity<PrenotazioneResponse> deletePrenotazione(@PathVariable Integer id) {
        //ELIMINA UNA PRENOTAZIONE
        PrenotazioneResponse response = prenotazioneService.deletePrenotazioneById(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}