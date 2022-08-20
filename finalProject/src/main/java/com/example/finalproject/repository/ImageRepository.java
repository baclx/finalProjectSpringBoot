package com.example.finalproject.repository;

import com.example.finalproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT i FROM Image i WHERE i.tables = ?1")
    Image findImageByTableId(Long id);
}
