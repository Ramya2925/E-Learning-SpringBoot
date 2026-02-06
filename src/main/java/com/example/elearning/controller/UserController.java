package com.example.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.elearning.model.User;
import com.example.elearning.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET user by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // POST register user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!";
        }
        user.setRole("STUDENT"); // default role
        userRepository.save(user);
        return "User registered successfully!";
    }

    // POST login user
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            if(existingUser.get().getPassword().equals(user.getPassword())) {
                return "Login successful!";
            } else {
                return "Invalid password!";
            }
        }
        return "User not found!";
    }
}
