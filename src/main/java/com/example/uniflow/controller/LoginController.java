package com.example.uniflow.controller;

import com.example.uniflow.entity.User;
import com.example.uniflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userService.findByUserName(loginRequest.getUserName());
        String errorMessage = "";

        if (!userOpt.isPresent()) {
            userOpt = userService.findByEmail(loginRequest.getEmail());
            errorMessage = "email";
        } else {
            errorMessage = "username";
        }

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", userOpt.get().getRole());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            if (!userOpt.isPresent()) {
                errorMessage = "Invalid username/email";
                response.put("error", errorMessage);
            } else if (!userOpt.get().getPassword().equals(loginRequest.getPassword())) {
                errorMessage = "Invalid Password";
                response.put("error", errorMessage);
            }
            return ResponseEntity.status(401).body(response);        }
    }

}
