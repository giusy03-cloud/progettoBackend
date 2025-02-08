package com.dipartimento.projecthotelnew.dao;

import com.dipartimento.projecthotelnew.model.Camera;

import java.util.List;

// Interfaccia per le operazioni CRUD legate alle camere (Camera) nel database
public interface CameraDAO {

    // Metodo per ottenere una camera tramite il suo ID
    Camera getCameraById(Integer id);

    // Metodo per ottenere tutte le camere
    List<Camera> getAllCamere();

    // Metodo per salvare una camera (aggiornamento)
    void saveCamera(Camera camera);

    // Metodo per aggiornare la disponibilità di una camera
    boolean updateDisponibilita(Integer cameraId, Boolean disponibilita);


}
