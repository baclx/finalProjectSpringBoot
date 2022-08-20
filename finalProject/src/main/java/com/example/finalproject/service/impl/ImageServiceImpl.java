package com.example.finalproject.service.impl;

import com.example.finalproject.model.Image;
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
    public Image findImageByTableId(Long id) {
        return imageRepository.findImageByTableId(id);
    }

    @Override
    public List<Image> listAll() {
        return imageRepository.findAll();
    }


}
