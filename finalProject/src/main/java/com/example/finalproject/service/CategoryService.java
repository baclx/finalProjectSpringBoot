package com.example.finalproject.service;

import com.example.finalproject.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listAll();

    void save(Category category);
}
