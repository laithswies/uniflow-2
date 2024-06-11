package com.example.uniflow.controller;

import com.example.uniflow.entity.User;
import com.example.uniflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
