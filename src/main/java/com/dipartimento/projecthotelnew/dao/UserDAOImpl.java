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

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(User user) {
        if(user.getRole()=="admin"){
            if(!checkEndsWith(user.getUsername())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username non valido per un admin");
            }
        }
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole());}

    private boolean checkEndsWith(String username) {
        String[] parts = username.split("@");
        String admin = parts[1];
        if(admin.equals("admin")) return true;
        return false;
}

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void deleteById(int id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.err.println("Errore durante l'eliminazione dell'utente con ID: " + id);
            e.printStackTrace(); // Puoi usare anche un logger per registrare l'errore
            throw new RuntimeException("Errore durante l'eliminazione dell'utente", e);
        }
    }


    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null; // Nessun utente trovato
        }
    }



}