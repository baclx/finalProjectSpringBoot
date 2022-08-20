package com.example.finalproject.service;

import com.example.finalproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();

    void save(User user);

    void delete(Long id);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
