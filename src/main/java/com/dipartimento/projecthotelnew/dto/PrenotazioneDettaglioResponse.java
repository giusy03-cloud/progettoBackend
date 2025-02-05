package com.dipartimento.projecthotelnew.dto;

public class PrenotazioneDettaglioResponse {

    private Integer id;         // ID della prenotazione
    private String nomeUtente;  // Nome dell'utente
    private String cameraNome;  // Nome della camera
    private String status;      // Status della prenotazione (opzionale)
    private String message;     // Messaggio personalizzato (opzionale)

    // Costruttori
    public PrenotazioneDettaglioResponse() {}

    public PrenotazioneDettaglioResponse(Integer id, String nomeUtente, String cameraNome, String status) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.cameraNome = cameraNome;
        this.status = status;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getCameraNome() {
        return cameraNome;
    }

    public void setCameraNome(String cameraNome) {
        this.cameraNome = cameraNome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
