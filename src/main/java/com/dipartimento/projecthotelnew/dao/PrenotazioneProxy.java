package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrenotazioneProxy implements PrenotazioneDAO {

    private PrenotazioneDAOImpl prenotazioneDAO;

    @Autowired
    public PrenotazioneProxy(PrenotazioneDAOImpl prenotazioneDAO) {
        this.prenotazioneDAO = prenotazioneDAO;
    }

    // 1. Ottieni una prenotazione per ID
    @Override
    public Prenotazione getPrenotazioneById(Integer id) {
        return prenotazioneDAO.getPrenotazioneById(id);
    }

    // 2. Ottieni tutte le prenotazioni con dettagli
    @Override
    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        return prenotazioneDAO.getAllPrenotazioniConDettagli();
    }

    // 3. Salva una nuova prenotazione
    @Override
    public void savePrenotazione(Prenotazione prenotazione) {
        prenotazioneDAO.savePrenotazione(prenotazione);
    }

    // 4. Elimina una prenotazione
    @Override
    public void deletePrenotazione(Integer id) {
        prenotazioneDAO.deletePrenotazione(id);
    }
}
