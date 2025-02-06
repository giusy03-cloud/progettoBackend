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

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        userService.register(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
        User foundUser = userService.findByUsername(user.getUsername());

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Utente non trovato", null, null, null));
        }


        if (!user.getPassword().equals(foundUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Password errata", null, null, null));
        }

        session.setAttribute("user", foundUser);


        String role = (foundUser.getRole() != null) ? foundUser.getRole() : "user";

        return ResponseEntity.ok(new LoginResponse(
                "Login effettuato con successo",
                foundUser.getUsername(),
                role,
                foundUser.getId() // AGGIUNTO userId
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





    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUSer(@PathVariable int id){
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(HttpSession session) {

        session.invalidate();

        LogoutResponse logoutResponse = new LogoutResponse("Logout effettuato con successo");

        return ResponseEntity.ok(logoutResponse);
    }

}