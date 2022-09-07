package com.example.finalproject.repository;

import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Tables;
import com.example.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderTableRepository extends JpaRepository<OrderTable, Long> {
    List<OrderTable> findAllByOrderByIdDesc();

    @Query("select t from OrderTable t where t.tables = ?1")
    Optional<OrderTable> findOrderTableByTableID(Tables table);

    @Query("select u from OrderTable u where u.user = ?1 order by u.createdAt desc")
    List<OrderTable> findOrderTableByUserID(User user);

    @Query("SELECT count(id) from OrderTable")
    Long countAllOrder();
}
