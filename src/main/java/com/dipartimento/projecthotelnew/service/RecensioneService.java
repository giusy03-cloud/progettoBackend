package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.RecensioneDAO;
import com.dipartimento.projecthotelnew.model.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneDAO recensioneDAO;


    // Metodo per aggiungere una nuova recensione
    public void addRecensione(Recensione recensione) {
        recensioneDAO.addRecensione(recensione);
    }

    // Metodo per ottenere tutte le recensioni

    public List<Recensione> getAllRecensioni() {
        return recensioneDAO.getAllRecensioni();
    }

    // Metodo per ottenere tutte le recensioni di un utente specifico
    public List<Recensione> getRecensioniByUserId(int userId) {
        return recensioneDAO.getRecensioniByUserId(userId);
    }

    // Metodo per eliminare una recensione specifica
    public void deleteRecensione(int id) {
        recensioneDAO.deleteRecensione(id);
    }
}
