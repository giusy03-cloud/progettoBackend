package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Recensione;

import java.util.List;

public interface RecensioneDAO {

    int addRecensione(Recensione recensione);

    List<Recensione> getAllRecensioni();

    void deleteRecensione(int id);

    List<Recensione> getRecensioniByUserId(int userId);
}
