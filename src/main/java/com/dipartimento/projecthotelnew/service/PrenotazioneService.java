package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.PrenotazioneDAO;
import com.dipartimento.projecthotelnew.dao.PrenotazioneProxy;
import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.dto.PrenotazioneResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneProxy prenotazioneProxy;

    // 1. Ottieni una prenotazione per ID

    public Prenotazione getPrenotazioneById(Integer id) {
        return prenotazioneProxy.getPrenotazioneById(id);  // Usa il Proxy
    }


    // 2. Ottieni tutte le prenotazioni

    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        return prenotazioneProxy.getAllPrenotazioniConDettagli();  // Usa il Proxy
    }

    public PrenotazioneResponse savePrenotazione(Prenotazione prenotazione) {
        try {
            // Salva tramite il Proxy
            prenotazioneProxy.savePrenotazione(prenotazione);
            return new PrenotazioneResponse("Prenotazione effettuata con successo", true);
        } catch (Exception e) {
            // Gestisci l'errore e ritorna la risposta di errore
            return new PrenotazioneResponse("Errore durante la prenotazione: " + e.getMessage(), false);
        }
    }


    // Metodo per eliminare una prenotazione per ID
    public PrenotazioneResponse deletePrenotazioneById(Integer id) {
        try {
            // Elimina tramite il Proxy
            prenotazioneProxy.deletePrenotazione(id);
            return new PrenotazioneResponse("Prenotazione eliminata con successo", true);
        } catch (Exception e) {
            return new PrenotazioneResponse("Errore durante la cancellazione della prenotazione: " + e.getMessage(), false);
        }
    }

}
