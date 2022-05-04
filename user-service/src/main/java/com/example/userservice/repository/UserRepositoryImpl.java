package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users;

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public User save(User user) {
        findUserById(user.getId()).ifPresent(i -> {
            throw new RuntimeException("User with id " + i.getId() + " already exists!");
        });
        this.users.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return users.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public User getUserById(String id) {
        return findUserById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found!"));
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = findUserById(user.getId()).orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found!"));
        int index = users.indexOf(existingUser);
        users.remove(index);
        users.add(index, user);

        return user;
    }

    @Override
    public boolean deleteUser(String id) {
        User existingUser = findUserById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found!"));
        return users.remove(existingUser);
    }
}
