package com.dipartimento.projecthotelnew.dto;

public class LogoutResponse {
    private String message;  // Messaggio di successo o errore

    // Costruttore per inizializzare la risposta con un messaggio
    public LogoutResponse(String message) {
        this.message = message;
    }

    // Getter per il messaggio
    public String getMessage() {
        return message;
    }
}
