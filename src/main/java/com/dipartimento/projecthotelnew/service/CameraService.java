package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.CameraDAO;
import com.dipartimento.projecthotelnew.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    @Autowired
    private CameraDAO cameraDAO;  // O CameraRepository se usi Spring Data JPA

    public List<Camera> getAllCamere() {
        return cameraDAO.getAllCamere();
    }

    public Camera getCameraById(Integer id) {
        return cameraDAO.getCameraById(id);
    }

    public boolean updateDisponibilita(Integer cameraId, Boolean disponibilita) {
        Camera camera = cameraDAO.getCameraById(cameraId);
        if (camera != null) {
            camera.setDisponibilita(disponibilita);
            cameraDAO.saveCamera(camera);  // Salva la camera con la disponibilità aggiornata
            return true;
        }
        return false;
    }

    public boolean prenotaCamera(Integer cameraId) {
        Camera camera = cameraDAO.getCameraById(cameraId);
        if (camera != null && camera.isDisponibilita()) {
            camera.setDisponibilita(false);  // Imposta la camera come prenotata
            cameraDAO.saveCamera(camera);  // Salva lo stato aggiornato
            return true;
        }
        return false;
    }
    public boolean resetDisponibilita(Integer cameraId, Boolean disponibilita) {
        // Chiamata al metodo nel DAO per aggiornare la disponibilità di una specifica camera
        return cameraDAO.updateDisponibilita(cameraId, disponibilita);  // Passa i parametri necessari
    }

    public void saveCamera(Camera camera){
        cameraDAO.saveCamera(camera);
    }





}