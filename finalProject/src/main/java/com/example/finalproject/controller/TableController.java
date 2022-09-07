package com.example.finalproject.controller;

import com.example.finalproject.model.Category;
import com.example.finalproject.model.Image;
import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Tables;
import com.example.finalproject.service.CategoryService;
import com.example.finalproject.service.ImageService;
import com.example.finalproject.service.OrderTableService;
import com.example.finalproject.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequestMapping("admin/table")
public class TableController {
    private static final String UPLOAD_DIR = "src/main/resources/static/images/table";
    @Autowired
    TableService tableService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageService imageService;

    @Autowired
    OrderTableService orderTableService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Tables> tableLists = tableService.listAll();

        model.addAttribute("title", "Table");
        model.addAttribute("tableLists", tableLists);

        return "admin/table/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        List<Category> categoryLists = categoryService.listAll();

        model.addAttribute("table", new Tables());
        model.addAttribute("title", "Add Table");

        model.addAttribute("categoryLists", categoryLists);

        return "admin/table/create";
    }

    @PostMapping("/store")
    public String store(
            Tables tables,
            RedirectAttributes ra,
            @RequestParam(name = "_image") MultipartFile file
    ) {

//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        try {
//            Path path = Paths.get(UPLOAD_DIR);
//            image.setImageName("/images/table/" + fileName);
//            image.setTables(tables);
//            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        List<String> fileNames = new ArrayList<>();
//
//        Arrays.stream(files).forEach(file -> {
//            try {
//                Path path = Paths.get(UPLOAD_DIR);
//                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
//                fileNames.add(file.getOriginalFilename());
//                image.setImageName("/images/table/" + fileNames);
//                image.setTables(tables);
//                imageService.save(image);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        tableService.save(tables);
//        imageService.save(image);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path path = Paths.get(UPLOAD_DIR);

            tables.setImgPrimary("/images/table/" + fileName);

            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableService.save(tables);

        ra.addFlashAttribute("msg", "Create Success");
        return "redirect:/admin/table";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra,
            Tables tables
    ) {
        Optional<OrderTable> orderTable = orderTableService.findOrderByTable(tables);
        Optional<Image> image = imageService.findImageByTableId(tables);

        if (orderTable.isPresent() || image.isPresent()) {
            ra.addFlashAttribute("wn", "id ban` nay` co' trong order || image -> k xoa' dc.");
            return "redirect:/admin/table";
        }

        tableService.deleteById(id);
        ra.addFlashAttribute("msg", "Ok");
        return "redirect:/admin/table";
    }
}
