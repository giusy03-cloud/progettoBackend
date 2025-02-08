package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    // Iniezione della dipendenza JdbcTemplate per interagire con il database
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Metodo per salvare un utente nel database
    public void save(User user) {
        // Verifica se l'utente è un admin e controlla se l'username termina con "@admin"
        if(user.getRole()=="admin"){
            if(!checkEndsWith(user.getUsername())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username non valido per un admin");
            }
        }
        // SQL per inserire l'utente nel database
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        // Esegui la query per inserire l'utente
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole());}

    // Metodo privato che verifica se l'username termina con "admin"
    private boolean checkEndsWith(String username) {
        String[] parts = username.split("@");
        String admin = parts[1]; // Prende la parte dopo "@"
        if(admin.equals("admin")) return true;
        return false;
}

    // Metodo per trovare un utente tramite ID
    @Override
    public User findById(int id) {
        // SQL per trovare l'utente tramite ID
        String sql = "SELECT * FROM users WHERE id = ?";
        // Esegui la query e restituisci l'utente trovato
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    // Metodo per ottenere tutti gli utenti dal database
    @Override
    public List<User> findAll() {
        // SQL per selezionare tutti gli utenti
        String sql = "SELECT * FROM users";
        // Esegui la query e restituisci la lista di utenti
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    // Metodo per eliminare un utente tramite ID
    @Override
    public void deleteById(int id) {
        try {
            // SQL per eliminare l'utente tramite ID

            String sql = "DELETE FROM users WHERE id = ?";
            // Esegui la query per eliminare l'utente
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Errore durante l'eliminazione dell'utente con ID: " + id);
            e.printStackTrace(); // Puoi usare anche un logger per registrare l'errore
            throw new RuntimeException("Errore durante l'eliminazione dell'utente", e);
        }
    }

    // Metodo per trovare un utente tramite username
    @Override
    public User findByUsername(String username) {
        // SQL per trovare l'utente tramite username
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            // Esegui la query per trovare l'utente
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null; // Nessun utente trovato
        }
    }



}