package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.dto.PrenotazioneDettaglioResponse;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Override
    public void savePrenotazione(Prenotazione prenotazione) {
        String sql = "INSERT INTO prenotazioni (userId, cameraId, nomeUtente) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, prenotazione.getUserId(), prenotazione.getCameraId(), prenotazione.getNomeUtente());
    }

    @Override
    public void deletePrenotazione(Integer id) {
        String sql = "DELETE FROM prenotazioni WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
