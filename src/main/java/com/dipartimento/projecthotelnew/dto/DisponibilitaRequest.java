package com.dipartimento.projecthotelnew.dto;

public class DisponibilitaRequest {
    private Integer cameraId;
    private Boolean disponibilita;

    public DisponibilitaRequest() {
    }

    public DisponibilitaRequest(Integer cameraId, Boolean disponibilita) {
        this.cameraId = cameraId;
        this.disponibilita = disponibilita;
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
}
