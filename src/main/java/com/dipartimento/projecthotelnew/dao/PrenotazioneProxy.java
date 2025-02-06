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

    @Override
    public Prenotazione getPrenotazioneById(Integer id) {
        return prenotazioneDAO.getPrenotazioneById(id);
    }

    @Override
    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        return prenotazioneDAO.getAllPrenotazioniConDettagli();
    }

    @Override
    public void savePrenotazione(Prenotazione prenotazione) {
        prenotazioneDAO.savePrenotazione(prenotazione);
    }

    @Override
    public void deletePrenotazione(Integer id) {
        prenotazioneDAO.deletePrenotazione(id);
    }
}