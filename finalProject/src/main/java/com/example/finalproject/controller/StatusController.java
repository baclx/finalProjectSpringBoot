package com.example.finalproject.controller;

import com.example.finalproject.model.Status;
import com.example.finalproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/status")
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Status> statusLists = statusService.listAll();

        model.addAttribute("title", "Status");
        model.addAttribute("statusLists", statusLists);

        return "admin/status/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        model.addAttribute("status", new Status());
        model.addAttribute("title", "Add Status");

        return "admin/status/create";
    }

    @PostMapping("/store")
    public String store(
            Status status,
            RedirectAttributes ra
    ) {
        statusService.save(status);

       ra.addFlashAttribute("msg", "Create Success");

        return "redirect:/admin/status";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra
    ) {
        statusService.delete(id);

        ra.addFlashAttribute("msg", "Delete Success");

        return "redirect:/admin/status";
    }
}
