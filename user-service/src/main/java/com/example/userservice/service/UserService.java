package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepositoryImpl repository;

    public UserService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User updateUser(User user) {
        return repository.updateUser(user);
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public boolean deleteUser(String id) {
        return repository.deleteUser(id);
    }

    public User getUser(String id) {
        return repository.getUserById(id);
    }
}
