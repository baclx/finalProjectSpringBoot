package com.example.finalproject.controller;

import com.example.finalproject.model.Role;
import com.example.finalproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Role> roleLists = roleService.listAll();

        model.addAttribute("roleLists", roleLists);
        model.addAttribute("title", "Role");

//        model.addAttribute("standardDate", new Date());
//        model.addAttribute("localDateTime", LocalDateTime.now());
//        model.addAttribute("localDate", LocalDate.now());
//        model.addAttribute("timestamp", Instant.now());

        return "admin/role/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        model.addAttribute("role", new Role());
        model.addAttribute("title", "Create Role");

        return "admin/role/create";
    }

//    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new Java8TimeDialect());
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }

    @PostMapping("/store")
    public String store(
            Role role,
            RedirectAttributes ra
    ) {
        roleService.save(role);
        ra.addFlashAttribute("msg","Create Success");

        return "redirect:/admin/role";
    }
}
