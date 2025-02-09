package com.dipartimento.projecthotelnew.dto;

public class DisponibilitaRequest {
    private Integer cameraId;  // ID della camera di cui si richiede la disponibilità
    private Boolean disponibilita;// Indica se la camera è disponibile o meno
    private Integer utenteLoggato;

    public DisponibilitaRequest() {
    }

    // Costruttore con parametri per inizializzare cameraId e disponibilita
    public DisponibilitaRequest(Integer cameraId, Boolean disponibilita, Integer utenteLoggato) {
        this.cameraId = cameraId;
        this.disponibilita = disponibilita;
        this.utenteLoggato = utenteLoggato;
    }
    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Boolean disponibilita) {
        this.disponibilita = disponibilita;
    }
    public Integer getUtenteLoggato() {
        return utenteLoggato;
    }
    public void setUtenteLoggato(Integer utenteLoggato) {
        this.utenteLoggato = utenteLoggato;
    }
}
