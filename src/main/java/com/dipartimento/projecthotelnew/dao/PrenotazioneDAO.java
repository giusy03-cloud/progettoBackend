package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;

import java.util.List;

public interface PrenotazioneDAO {

    Prenotazione getPrenotazioneById(Integer id);

    List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli();

    void savePrenotazione(Prenotazione prenotazione);

    void deletePrenotazione(Integer id);
}