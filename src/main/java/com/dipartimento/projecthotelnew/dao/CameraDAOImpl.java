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
        try {
            String sql = "SELECT * FROM camere WHERE id = ?";
            Camera camera = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Camera.class), id);
            return camera;
        } catch (Exception e) {
            return null;
        }
    }



    @Override
    public List<Camera> getAllCamere() {
        String sql = "SELECT * FROM camere";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Camera.class));
    }


    @Override
    public void saveCamera(Camera camera) {
        String sql = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        jdbcTemplate.update(sql, camera.isDisponibilita(), camera.getId());
    }



    @Override
    public boolean updateDisponibilita(Integer cameraId, Boolean disponibilita) {
        String sql = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, disponibilita, cameraId);
        return rowsUpdated > 0;
    }



}
