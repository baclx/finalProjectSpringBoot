package com.example.finalproject.service;

import com.example.finalproject.model.Tables;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TableService {
    Page<Tables> listAll(int pageNumber);

    List<Tables> listAll();

    void save(Tables tables);

    Optional<Tables> findById(Long id);
}
