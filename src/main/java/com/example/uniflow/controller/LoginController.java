package com.example.uniflow.controller;

import com.example.uniflow.entity.User;
import com.example.uniflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userService.findByUserName(loginRequest.getUserName());

        if (!userOpt.isPresent()) {
            userOpt = userService.findByEmail(loginRequest.getEmail());
        }

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username/email or password");
        }
    }
}
