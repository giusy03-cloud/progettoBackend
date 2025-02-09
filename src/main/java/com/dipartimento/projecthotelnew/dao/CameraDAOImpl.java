package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Camera;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CameraDAOImpl implements CameraDAO {

    //E' UNA CLASSE CHE SEMPLIFICA L'INTERAZIONE CON I DATABASE UTILIZZANDO JDBC
    private final JdbcTemplate jdbcTemplate;

    // Costruttore: inietta il JdbcTemplate per interagire con il database
    public CameraDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static Camera getCamera(Integer cameraId) {
        CameraDAOImpl cameraDAO = new CameraDAOImpl(new JdbcTemplate());
        return cameraDAO.getCamera(cameraId);
    }
    // Implementazione del metodo per ottenere una camera per ID
    @Override
    public Camera getCameraById(Integer id) {
        try {
            // Query SQL per cercare una camera tramite l'ID
            String sql = "SELECT * FROM camere WHERE id = ?";
            // Esegue la query e mappa i risultati sulla classe Camera
            Camera camera = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Camera.class), id);
            return camera;
        } catch (Exception e) {
            return null;
        }
    }

    // Implementazione del metodo per ottenere tutte le camere
    @Override
    public List<Camera> getAllCamere() {
        // Query SQL per ottenere tutte le camere
        String sql = "SELECT * FROM camere";
        // Esegue la query e mappa i risultati su una lista di oggetti Camera
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Camera.class));
    }

    // Implementazione del metodo per salvare/aggiornare una camera

    @Override
    public void saveCamera(Camera camera) {
        // Query SQL per aggiornare la disponibilità di una camera
        String sql = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        // Esegue l'aggiornamento nel database
        jdbcTemplate.update(sql, camera.isDisponibilita(), camera.getId());
    }


    // Implementazione del metodo per aggiornare la disponibilità di una camera
    @Override
    public boolean updateDisponibilita(Integer cameraId, Boolean disponibilita) {
        // Query SQL per aggiornare la disponibilità di una camera
        String sql = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        // Esegue l'aggiornamento nel database e controlla quante righe sono state aggiornate
        int rowsUpdated = jdbcTemplate.update(sql, disponibilita, cameraId);
        // Restituisce true se sono state aggiornate più di zero righe, altrimenti false
        return rowsUpdated > 0;
    }



}
