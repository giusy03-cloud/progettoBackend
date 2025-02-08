package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.User;

import java.util.List;

public interface UserDAO {

    // Metodo per salvare un utente nel database
    void save(User user);

    // Metodo per trovare un utente per ID
    User findById(int id);

    // Metodo per ottenere tutti gli utenti
    List<User> findAll();

    // Metodo per trovare un utente per username
    User findByUsername(String username);

    // Metodo per eliminare un utente per ID
    void deleteById(int id);

}

