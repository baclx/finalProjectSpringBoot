package com.example.finalproject.repository;

import com.example.finalproject.model.Image;
import com.example.finalproject.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT i FROM Image i WHERE i.tables = ?1")
    Optional<Image> findImageByTableId(Tables table);
}
