package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Camera;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CameraDAOImpl implements CameraDAO {

    private final JdbcTemplate jdbcTemplate;

    public CameraDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Camera getCameraById(Integer id) {
        String sql = "SELECT * FROM camere WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Camera.class), id);
    }

    @Override
    public List<Camera> getAllCamere() {
        String sql = "SELECT * FROM camere";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Camera.class));
    }

    @Override
    public void saveCamera(Camera camera) {
        String sql = "INSERT INTO camere (nome, tipo, prezzo, disponibilita) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, camera.getNome(), camera.getTipo(), camera.getPrezzo(), camera.getDisponibilita());
    }

    @Override
    public boolean updateDisponibilita(Integer cameraId, Boolean disponibilita) {
        String sql = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, disponibilita, cameraId);

        // Restituisci true se è stato aggiornato almeno un record, altrimenti false
        return rowsUpdated > 0;
    }


}
