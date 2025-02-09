package com.dipartimento.projecthotelnew.model;


public class Prenotazione {

    private Integer id;

    private Integer userId;
    private Integer cameraId;
    private String nomeUtente;

    public Prenotazione() {

    }

    public Prenotazione(Integer userId, Integer cameraId, String nomeUtente) {
        this.userId = userId;
        this.cameraId = cameraId;
        this.nomeUtente = nomeUtente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", userId=" + userId +
                ", cameraId=" + cameraId +
                ", nomeUtente='" + nomeUtente + '\'' +
                '}';
    }
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }
}
