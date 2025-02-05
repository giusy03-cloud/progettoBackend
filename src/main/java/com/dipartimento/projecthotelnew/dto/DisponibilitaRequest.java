package com.dipartimento.projecthotelnew.dto;

public class DisponibilitaRequest {
    private Integer cameraId;
    private Boolean disponibilita;

    // Costruttore di default
    public DisponibilitaRequest() {
    }

    // Costruttore con parametri (opzionale, se lo usi)
    public DisponibilitaRequest(Integer cameraId, Boolean disponibilita) {
        this.cameraId = cameraId;
        this.disponibilita = disponibilita;
    }
    // Getters and Setters
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
}
