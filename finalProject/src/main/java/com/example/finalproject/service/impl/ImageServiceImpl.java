package com.example.finalproject.service.impl;

import com.example.finalproject.model.Image;
import com.example.finalproject.model.Tables;
import com.example.finalproject.repository.ImageRepository;
import com.example.finalproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Optional<Image> findImageByTableId(Tables table) {
        return imageRepository.findImageByTableId(table);
    }

    @Override
    public List<Image> listAll() {
        return imageRepository.findAll();
    }


}
