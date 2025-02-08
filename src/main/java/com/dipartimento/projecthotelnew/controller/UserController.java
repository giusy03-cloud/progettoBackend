package com.dipartimento.projecthotelnew.controller;


import com.dipartimento.projecthotelnew.dto.LoginResponse;
import com.dipartimento.projecthotelnew.dto.LogoutResponse;
import com.dipartimento.projecthotelnew.model.User;
import com.dipartimento.projecthotelnew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Questa classe è un controller REST che gestisce le operazioni relative agli utenti
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Iniezione del servizio UserService per accedere ai metodi logici legati agli utenti
    @Autowired
    private UserService userService;

    // Endpoint per registrare un nuovo utente
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        userService.register(user); // Chiama il servizio per registrare l'utente
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    // Endpoint per il login di un utente
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
        // Cerca l'utente nel database utilizzando il nome utente
        User foundUser = userService.findByUsername(user.getUsername());

        // Controlla se l'utente non esiste
        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Utente non trovato", null, null, null));
        }
        // Controlla se la password fornita non corrisponde a quella salvata
        if (!user.getPassword().equals(foundUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Password errata", null, null, null));
        }

        // Salva l'utente nella sessione corrente
        session.setAttribute("user", foundUser);
        // Determina il ruolo dell'utente, con un valore predefinito "user" se il ruolo è nullo
        String role = (foundUser.getRole() != null) ? foundUser.getRole() : "user";

        // Restituisce una risposta con i dettagli dell'utente dopo il login
        return ResponseEntity.ok(new LoginResponse(
                "Login effettuato con successo",
                foundUser.getUsername(),
                role,
                foundUser.getId()  // Aggiunto l'ID utente alla risposta
        ));
    }



    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(user);
    }


    // Endpoint per ottenere la lista di tutti gli utenti
    @GetMapping
    public List<User> getAllUsers(){
        // Restituisce una lista di tutti gli utenti recuperata tramite il servizio
        return userService.getAllUsers();
    }

    // Endpoint per eliminare un utente dato il suo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUSer(@PathVariable int id){
        // Recupera l'utente tramite il suo ID
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        // Elimina l'utente tramite il servizio
        userService.deleteUser(id);
        // Restituisce i dati dell'utente eliminato
        return ResponseEntity.ok(user);
    }

    // Endpoint per effettuare il logout
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(HttpSession session) {
        // Invalida la sessione corrente (logout effettivo)
        session.invalidate();

        // Crea un oggetto LogoutResponse con un messaggio di successo
        LogoutResponse logoutResponse = new LogoutResponse("Logout effettuato con successo");

        // Restituisce una risposta HTTP con il messaggio di logout
        return ResponseEntity.ok(logoutResponse);
    }

}