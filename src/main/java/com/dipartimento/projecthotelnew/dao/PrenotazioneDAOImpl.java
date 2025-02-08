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

    // Iniezione del JdbcTemplate per interagire con il database
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Recupera una prenotazione dal database dato il suo ID
    @Override
    public Prenotazione getPrenotazioneById(Integer id) {
        String sql = "SELECT * FROM prenotazioni WHERE id = ?";
        // Esegui la query e mappa il risultato su un oggetto Prenotazione
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Prenotazione.class), id);
    }

    // Recupera tutte le prenotazioni con i dettagli associati (nome utente, nome camera)
    @Override
    public List<PrenotazioneDettaglioResponse> getAllPrenotazioniConDettagli() {
        // Query SQL per unire le tabelle prenotazioni, users e camere
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
        // Esegui la query e mappa il risultato su oggetti PrenotazioneDettaglioResponse
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PrenotazioneDettaglioResponse.class));
    }

    // Salva una nuova prenotazione e aggiorna la disponibilità della camera
    @Transactional
    @Override
    public void savePrenotazione(Prenotazione prenotazione) {

        // Inserisci una nuova prenotazione nel database
        String sql = "INSERT INTO prenotazioni (userId, cameraId, nomeUtente) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, prenotazione.getUserId(), prenotazione.getCameraId(), prenotazione.getNomeUtente());

        // Aggiorna la disponibilità della camera, impostandola come non disponibile (false)
        String sqlUpdateCamera = "UPDATE camere SET disponibilita = false WHERE id = ?";
        jdbcTemplate.update(sqlUpdateCamera, prenotazione.getCameraId());


    }

    // Elimina una prenotazione e ripristina la disponibilità della camera
    @Transactional
    @Override
    public void deletePrenotazione(Integer id) {
        // Recupera l'ID della camera associata alla prenotazione
        String sqlSelectCamera = "SELECT cameraId FROM prenotazioni WHERE id = ?";
        Integer cameraId = jdbcTemplate.queryForObject(sqlSelectCamera, Integer.class, id);

        // Elimina la prenotazione dal database
        String sqlDelete = "DELETE FROM prenotazioni WHERE id = ?";
        jdbcTemplate.update(sqlDelete, id);

        // Ripristina la disponibilità della camera (impostandola come disponibile)
        String sqlUpdateCamera = "UPDATE camere SET disponibilita = true WHERE id = ?";
        jdbcTemplate.update(sqlUpdateCamera, cameraId);
    }
}