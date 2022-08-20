package com.example.finalproject.service.impl;

import com.example.finalproject.model.Tables;
import com.example.finalproject.repository.TableRepository;
import com.example.finalproject.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    TableRepository tableRepository;

    @Override
    public Page<Tables> listAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 4);
        return tableRepository.findAll(pageable);
    }

    @Override
    public List<Tables> listAll() {
        return (List<Tables>) tableRepository.findAll();
    }

    @Override
    public void save(Tables tables) {
        tableRepository.save(tables);
    }

    @Override
    public Optional<Tables> findById(Long id) {
        return tableRepository.findById(id);
    }
}
