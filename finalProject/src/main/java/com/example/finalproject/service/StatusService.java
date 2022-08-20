package com.example.finalproject.service;

import com.example.finalproject.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<Status> listAll();

    Optional<Status> findById(Long id);

    void save(Status status);

    void delete(Long id);
}
