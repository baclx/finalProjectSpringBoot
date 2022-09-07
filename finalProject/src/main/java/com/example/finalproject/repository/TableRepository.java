package com.example.finalproject.repository;

import com.example.finalproject.model.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TableRepository extends PagingAndSortingRepository<Tables, Long> {
    @Query("SELECT count(id) from Tables ")
    Long countAllTable();
}
