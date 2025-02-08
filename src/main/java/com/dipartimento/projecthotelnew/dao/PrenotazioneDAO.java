package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;

import java.util.List;

public interface PrenotazioneDAO {

    // Recupera una prenotazione dal database dato il suo ID
    Prenotazione getPrenotazioneById(Integer id);

    // Recupera tutte le prenotazioni con i dettagli associati (nome utente, nome camera)
    List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli();

    // Salva una nuova prenotazione nel database
    void savePrenotazione(Prenotazione prenotazione);

    // Elimina una prenotazione dato il suo ID
    void deletePrenotazione(Integer id);
}
