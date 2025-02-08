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

//SI USA PER DEFINIRE UNA CLASSE COME CONTROLLER CHE GESTISCE LE TICHIESTE HTTP IN UN'APPLICAZIONE WEB RESTful
//INDICA CHE LA CLASSE E' UN CONTROLLER E TUTTE LE RISPOSTE ALLE RICHIESTE HTTP
//SARANNO AUTOMATICAMENTE SCRITTE NEL CORPO DELLA RICHIESTA (IN QUESTO CASO IN FORMATO JSON)
//I JSON SONO UN FORMATO DI DATI, E' UN FORMATO DI SERIALIZZAZIONE DEI DATI,
//CHE PERMETTE DI RAPPRESENTARE OGGETTI,ARRAY,STRINGHE,NUMERI,VALORI BOOLEANI E NULL
//UN FILE JSON E'COMPOSTO DA UNA COPPIA CHIAVE-VALORE, DOVE LA CHIAVE E' UNA STRINGA E IL VALORE PUO' ESSERE
//DI DIVERSI TIPI COME STRINGA,NUMERO,OGGETTO
@RestController
//DEFINISCE URL BASE PER TUTTE LE RICHIESTE HTTP GESTITE DA UNA CLASSE
@RequestMapping("/api/camere")
public class CameraController {

    //QUESTA LASSE GESTISCE LE OPERAZIONI CRUD SULLE CAMERE D'ALBERGO E LA LOGICA
    //RELATIVA ALLA DISPONIBILITA' E PRENOTAZIONE


    //AUTOWIRED E' UN'ANNOTAZIONE DI SPRING UTILIZZATA PER ABILITARE
    //L'INJECTION DELLE DIPENDENZE--> E' UN PRINCIPIO DI PROGRAMMAZIONE CHE PERMETTE
    //DI FORNIRE OGGETTI O COMPONENTI A UNA CLASSE.
    //L'ANNOTAZIONE @AUTOWIRED DICE A SPRING DI CERCA UN'ISTANZA DI UNA CLASSE
    //COMPATIBILE CON IL TIPO DELLA DIPENDENZA RICHIESTA E DI INIETTARLA AUTOMATICAMENTE NELLA CLASSE IN CUI E' USATA
    @Autowired
    private CameraService cameraService;


    //SERVE PER GESTIRE LE RICHIESTE GET, CHE VENGONO UTILIZZATE PER RECUPERARE INFORMAZIONI DA UN SERVER
    @GetMapping
    public ResponseEntity<List<Camera>> getAllCamere() {
        //RECUPERA TUTTE LE CAMERE DISPONIBILI
        List<Camera> camere = cameraService.getAllCamere();
        return ResponseEntity.ok(camere);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable Integer id) {
        //RECUPERA UNA CAMERA TRAMITE ID
        Camera camera = cameraService.getCameraById(id);
        if (camera == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(camera);
    }

    //VIENE UTILIZZATO PER INVIARE DATI AL SERVER
    @PostMapping("/disponibilita/{id}")
    public ResponseEntity<String> updateDisponibilita(@PathVariable Integer id, @RequestBody Boolean disponibilita) {
        //AGGIORNA LA DISPONIBILITA' DI UNA CAMERA SPECIFICA
        boolean updated = cameraService.updateDisponibilita(id, disponibilita);
        if (updated) {
            return ResponseEntity.ok("Disponibilità aggiornata con successo");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Camera non trovata");
        }
    }


    @PostMapping("/prenota")
    public ResponseEntity<String> prenotaCamera(@RequestBody Prenotazione prenotazione) {
        //EFFETTUA UNA PRENOTAZIONE SE LA CAMERA E' DISPONIBILE
        Camera camera = cameraService.getCameraById(prenotazione.getCameraId());
        if (camera != null && camera.isDisponibilita()) {
            camera.setDisponibilita(false);
            //AGGIORNA LA CAMERA COME NON DISPONIBILE
            cameraService.saveCamera(camera);
            return ResponseEntity.ok("Prenotazione riuscita");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Camera non disponibile");
        }
    }



    @PostMapping("/resetDisponibilita")
    public ResponseEntity<String> resetDisponibilita(@RequestBody DisponibilitaRequest request) {
        //RESETTA LA DISPONIBILITA' DELLE CAMERE SPECIFICATE NELLA RICHIESTA
        boolean success = cameraService.resetDisponibilita(request.getCameraId(), request.getDisponibilita());
        if (success) {
            return ResponseEntity.ok("{\"message\": \"Disponibilità aggiornata con successo\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Errore nel ripristino della disponibilità della camera\"}");
        }
    }





}