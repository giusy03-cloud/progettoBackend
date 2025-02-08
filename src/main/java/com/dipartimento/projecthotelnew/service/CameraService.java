package com.dipartimento.projecthotelnew.service;

import com.dipartimento.projecthotelnew.dao.CameraDAO;
import com.dipartimento.projecthotelnew.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//L'ANNOTAZIONE @SERVICE INDICA CHE LA CLASSE E' UN SERVIZIO,I SERVIZI SONO CLASSI CHE
//CONTENGONO LA LOGICA DI BUSINESS DELL'APPLICAZIONE E SONO USATE PER INTERAGIRE CON I DATI
//SERVICE IMPLICA CHE SPIRNG CREERA' AUTOMATICAMENTE UN'ISTANZA DI QUELLA CLASSE E LA RENDERA' DISPONIBILE PER L'INIEZIONE
//IN ALTRI COMPONENTI
@Service
public class CameraService {

    //IN QUESTO MODO IL SERVIZIO PUO' UTILIZZARE IL DAO PER ACCEDERE E
    //MANIPOLARE I DATI RELATIVI ALLE CAMERE
    @Autowired
    private CameraDAO cameraDAO;

    //RECUPERO TUTTE LE CAMERE NEL DB
    public List<Camera> getAllCamere() {
        return cameraDAO.getAllCamere();
    }

    //CERCA UNA SINGOLA CAMERA TRAMITE IL SUO ID
    public Camera getCameraById(Integer id) {
        return cameraDAO.getCameraById(id);
    }

    //PERMETTE DI AGGIORNARE LA DISPONIBILITA' DI UNA CAMERA
    public boolean updateDisponibilita(Integer cameraId, Boolean disponibilita) {
        //CERCO LA CAMERA TRAMITE IL CAMERAID
        Camera camera = cameraDAO.getCameraById(cameraId);
        if (camera != null) {
            //AGGIORNA IL SUO STATO DI DISPONIBILITA'
            camera.setDisponibilita(disponibilita);
            //E POI LA SLAVA NUOVAMENTE NEL DB
            cameraDAO.saveCamera(camera);
            return true;
        }
        return false;
    }

    public boolean prenotaCamera(Integer cameraId) {
        Camera camera = cameraDAO.getCameraById(cameraId);
        if (camera != null && camera.isDisponibilita()) {
            camera.setDisponibilita(false);
            cameraDAO.saveCamera(camera);
            return true;
        }
        return false;
    }

    //RESETTO LA DISPONIBILITA' DELLA CAMERA
    public boolean resetDisponibilita(Integer cameraId, Boolean disponibilita) {

        return cameraDAO.updateDisponibilita(cameraId, disponibilita);
    }

    //SALVO O AGGIORNO LA CAMERA NEL DB
    public void saveCamera(Camera camera){
        cameraDAO.saveCamera(camera);
    }





}