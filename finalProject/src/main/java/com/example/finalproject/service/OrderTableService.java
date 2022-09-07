package com.example.finalproject.service;

import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Tables;
import com.example.finalproject.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderTableService {
    List<OrderTable> listAll();

    void save(OrderTable orderTable);

    Optional<OrderTable> findById(Long id);

    List<OrderTable> findAllByOrderByIdDesc();

    Optional<OrderTable> findOrderByTable(Tables tables);

    List<OrderTable> findOrderTableByUserID(User user);

    Long countAllOrder();
}
