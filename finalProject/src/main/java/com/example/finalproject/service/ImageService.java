package com.example.finalproject.service;

import com.example.finalproject.model.Image;
import com.example.finalproject.model.Tables;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> listAll();
    void save(Image image);

    Optional<Image> findImageByTableId(Tables table);
}
