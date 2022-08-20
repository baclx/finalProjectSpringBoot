package com.example.finalproject.controller;

import com.example.finalproject.model.Category;
import com.example.finalproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Category> categoryLists = categoryService.listAll();

        model.addAttribute("categoryLists", categoryLists);
        model.addAttribute("title", "Category");

        return "admin/category/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        model.addAttribute("category", new Category());
        model.addAttribute("title", "Created Category");

        return "admin/category/create";
    }

    @PostMapping("/store")
    public String store(
            Category category,
            RedirectAttributes ra
    ) {
        categoryService.save(category);
        ra.addFlashAttribute("msg", "Create Success");

        return "redirect:/admin/category";
    }
}
