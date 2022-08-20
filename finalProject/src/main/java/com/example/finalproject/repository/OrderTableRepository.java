package com.example.finalproject.repository;

import com.example.finalproject.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderTableRepository extends JpaRepository<OrderTable, Long> {
    List<OrderTable> findAllByOrderByIdDesc();
}
