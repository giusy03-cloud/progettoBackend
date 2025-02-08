package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.PrenotazioneDAO;
import com.dipartimento.projecthotelnew.dao.PrenotazioneProxy;
import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.dto.PrenotazioneResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Annotazione che definisce questa classe come un servizio per la gestione della logica di business
public class PrenotazioneService {

    // Iniezione automatica del bean PrenotazioneProxy
    @Autowired
    private PrenotazioneProxy prenotazioneProxy;


    // Metodo per recuperare una prenotazione in base al suo ID
    public Prenotazione getPrenotazioneById(Integer id) {
        return prenotazioneProxy.getPrenotazioneById(id); // Chiama il metodo nel proxy per ottenere la prenotazione
    }

    // Metodo per recuperare tutte le prenotazioni con dettagli aggiuntivi
    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        return prenotazioneProxy.getAllPrenotazioniConDettagli();  // Usa il Proxy
    }


    // Metodo per salvare una nuova prenotazione e restituire un oggetto di risposta con un messaggio
    public PrenotazioneResponse savePrenotazione(Prenotazione prenotazione) {
        try {

            prenotazioneProxy.savePrenotazione(prenotazione);
            return new PrenotazioneResponse("Prenotazione effettuata con successo", true);
        } catch (Exception e) {

            return new PrenotazioneResponse("Errore durante la prenotazione: " + e.getMessage(), false);
        }
    }


    // Metodo per eliminare una prenotazione e restituire un oggetto di risposta con un messaggio
    public PrenotazioneResponse deletePrenotazioneById(Integer id) {
        try {

            prenotazioneProxy.deletePrenotazione(id);
            return new PrenotazioneResponse("Prenotazione eliminata con successo", true);
        } catch (Exception e) {
            return new PrenotazioneResponse("Errore durante la cancellazione della prenotazione: " + e.getMessage(), false);
        }
    }

}