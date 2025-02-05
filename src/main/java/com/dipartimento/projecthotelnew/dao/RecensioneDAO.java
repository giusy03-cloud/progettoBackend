package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Recensione;

import java.util.List;

public interface RecensioneDAO {

    // Metodo per aggiungere una recensione
    int addRecensione(Recensione recensione);

    // Metodo per ottenere tutte le recensioni
    List<Recensione> getAllRecensioni();
    void deleteRecensione(int id);
    // Metodo per ottenere le recensioni di un utente specifico
    List<Recensione> getRecensioniByUserId(int userId);
}
