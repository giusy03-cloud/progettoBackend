package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.UserDAO;
import com.dipartimento.projecthotelnew.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    // Metodo per registrare un nuovo utente nel sistema
    public void register(User user){
        userDAO.save(user); // Salva l'utente nel database tramite il DAO
    }
    // Metodo per ottenere tutti gli utenti presenti nel sistema
    public List<User> getAllUsers(){
        return userDAO.findAll(); // Recupera tutti gli utenti dal database tramite il DAO
    }
    // Metodo per eliminare un utente specificato tramite il suo ID
    public void deleteUser(int id){
        userDAO.deleteById(id);  // Elimina l'utente dal database tramite il DAO
    }

    // Metodo per ottenere un utente specifico tramite il suo ID
    public User getUserById(int id){
        return userDAO.findById(id);  // Recupera l'utente dal database utilizzando il suo ID
    }
    // Metodo per cercare un utente tramite il suo nome utente
    public User findByUsername(String username){
        return userDAO.findByUsername(username);
    }


}
