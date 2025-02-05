package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Camera;

import java.util.List;

public interface CameraDAO {

    Camera getCameraById(Integer id);

    List<Camera> getAllCamere();

    void saveCamera(Camera camera);

    boolean updateDisponibilita(Integer cameraId, Boolean disponibilita);

}
