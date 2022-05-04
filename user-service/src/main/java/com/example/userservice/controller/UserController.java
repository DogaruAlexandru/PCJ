package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.saveUser(user));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.updateUser(user));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(service.deleteUser(userId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(service.getUser(userId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

}
