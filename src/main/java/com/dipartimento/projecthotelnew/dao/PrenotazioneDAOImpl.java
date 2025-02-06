package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PrenotazioneDAOImpl implements PrenotazioneDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Prenotazione getPrenotazioneById(Integer id) {
        String sql = "SELECT * FROM prenotazioni WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Prenotazione.class), id);
    }

    @Override
    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        String sql = """
        SELECT 
            p.id AS id,
            u.username AS nomeUtente,
            c.nome AS cameraNome
        FROM 
            prenotazioni p
        JOIN 
            users u ON p.userId = u.id
        JOIN 
            camere c ON p.cameraId = c.id
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PrenotazioneDettaglioResponse.class));
    }

    // Metodo per salvare la prenotazione
    @Transactional
    @Override
    public void savePrenotazione(Prenotazione prenotazione) {
        String sql = "INSERT INTO prenotazioni (userId, cameraId, nomeUtente) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, prenotazione.getUserId(), prenotazione.getCameraId(), prenotazione.getNomeUtente());

        // Aggiorna la disponibilità della camera (stesso metodo della prenotazione)
        String sqlUpdateCamera = "UPDATE camere SET disponibilita = false WHERE id = ?";
        jdbcTemplate.update(sqlUpdateCamera, prenotazione.getCameraId());


    }

    // Metodo per eliminare una prenotazione
    @Transactional
    @Override
    public void deletePrenotazione(Integer id) {
        // Ottieni il cameraId prima di eliminare la prenotazione
        String sqlSelectCamera = "SELECT cameraId FROM prenotazioni WHERE id = ?";
        Integer cameraId = jdbcTemplate.queryForObject(sqlSelectCamera, Integer.class, id);

        // Elimina la prenotazione
        String sqlDelete = "DELETE FROM prenotazioni WHERE id = ?";
        jdbcTemplate.update(sqlDelete, id);

        // Ripristina la disponibilità della camera
        String sqlUpdateCamera = "UPDATE camere SET disponibilita = true WHERE id = ?";
        jdbcTemplate.update(sqlUpdateCamera, cameraId);
    }
}