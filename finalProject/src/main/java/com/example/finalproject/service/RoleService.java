package com.example.finalproject.service;

import com.example.finalproject.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> listAll();

    void save(Role role);

    Optional<Role> findByName(String name);
}
