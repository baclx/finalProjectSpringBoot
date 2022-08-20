package com.example.finalproject.service;

import com.example.finalproject.model.OrderTable;

import java.util.List;
import java.util.Optional;

public interface OrderTableService {
    List<OrderTable> listAll();

    void save(OrderTable orderTable);

    Optional<OrderTable> findById(Long id);

    List<OrderTable> findAllByOrderByIdDesc();
}
