package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Recensione;

import java.util.List;

public interface RecensioneDAO {

    // Metodo per aggiungere una nuova recensione
    int addRecensione(Recensione recensione);

    // Metodo per ottenere tutte le recensioni
    List<Recensione> getAllRecensioni();

    // Metodo per eliminare una recensione specifica tramite id
    void deleteRecensione(int id);

    // Metodo per ottenere recensioni di un utente specifico tramite il suo id
    List<Recensione> getRecensioniByUserId(int userId);
}

