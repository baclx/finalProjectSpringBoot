package com.example.finalproject.service.impl;

import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Tables;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.OrderTableRepository;
import com.example.finalproject.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTableServiceImpl implements OrderTableService {
    @Autowired
    OrderTableRepository orderTableRepository;

    @Override
    public List<OrderTable> listAll() {
        return orderTableRepository.findAll();
    }

    @Override
    public void save(OrderTable orderTable) {
        orderTableRepository.save(orderTable);
    }

    @Override
    public Optional<OrderTable> findById(Long id) {
        return orderTableRepository.findById(id);
    }

    @Override
    public List<OrderTable> findAllByOrderByIdDesc() {
        return orderTableRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Optional<OrderTable> findOrderByTable(Tables tables) {
        return orderTableRepository.findOrderTableByTableID(tables);
    }

    @Override
    public List<OrderTable> findOrderTableByUserID(User user) {
        return orderTableRepository.findOrderTableByUserID(user);
    }

    @Override
    public Long countAllOrder() {
        return orderTableRepository.countAllOrder();
    }
}
