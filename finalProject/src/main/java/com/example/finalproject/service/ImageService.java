package com.example.finalproject.service;

import com.example.finalproject.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> listAll();
    void save(Image image);

    Image findImageByTableId(Long id);
}
