package com.dipartimento.projecthotelnew.controller;

import com.dipartimento.projecthotelnew.dto.DisponibilitaRequest;
import com.dipartimento.projecthotelnew.model.Camera;
import com.dipartimento.projecthotelnew.model.Prenotazione;
import com.dipartimento.projecthotelnew.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camere")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    public ResponseEntity<List<Camera>> getAllCamere() {
        List<Camera> camere = cameraService.getAllCamere();
        return ResponseEntity.ok(camere);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable Integer id) {
        Camera camera = cameraService.getCameraById(id);
        if (camera == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(camera);
    }

    @PostMapping("/disponibilita/{id}")
    public ResponseEntity<String> updateDisponibilita(@PathVariable Integer id, @RequestBody Boolean disponibilita) {
        boolean updated = cameraService.updateDisponibilita(id, disponibilita);
        if (updated) {
            return ResponseEntity.ok("Disponibilità aggiornata con successo");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Camera non trovata");
        }
    }


    // Controller Prenotazione
    @PostMapping("/prenota")
    public ResponseEntity<String> prenotaCamera(@RequestBody Prenotazione prenotazione) {
        Camera camera = cameraService.getCameraById(prenotazione.getCameraId());
        if (camera != null && camera.isDisponibilita()) {
            camera.setDisponibilita(false);  // Imposta la disponibilità su false
            cameraService.saveCamera(camera);  // Salva la camera con disponibilità aggiornata
            return ResponseEntity.ok("Prenotazione riuscita");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Camera non disponibile");
        }
    }



    @PostMapping("/resetDisponibilita")
    public ResponseEntity<String> resetDisponibilita(@RequestBody DisponibilitaRequest request) {
        boolean success = cameraService.resetDisponibilita(request.getCameraId(), request.getDisponibilita());
        if (success) {
            return ResponseEntity.ok("{\"message\": \"Disponibilità aggiornata con successo\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Errore nel ripristino della disponibilità della camera\"}");
        }
    }





}