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


    public void addRecensione(Recensione recensione) {
        recensioneDAO.addRecensione(recensione);
    }


    public List<Recensione> getAllRecensioni() {
        return recensioneDAO.getAllRecensioni();
    }


    public List<Recensione> getRecensioniByUserId(int userId) {
        return recensioneDAO.getRecensioniByUserId(userId);
    }


    public void deleteRecensione(int id) {
        recensioneDAO.deleteRecensione(id);
    }
}
