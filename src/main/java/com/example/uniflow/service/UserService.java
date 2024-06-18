package com.example.uniflow.service;

import com.example.uniflow.entity.User;
import com.example.uniflow.exception.UserAlreadyExistsException;
import com.example.uniflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists: " + user.getEmail());
        }
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + user.getUserName());
        }
        return userRepository.save(user);
    }
}
