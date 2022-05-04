package com.example.userservice.repository;

import com.example.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findUserById(String id);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(User user);

    boolean deleteUser(String id);
}
