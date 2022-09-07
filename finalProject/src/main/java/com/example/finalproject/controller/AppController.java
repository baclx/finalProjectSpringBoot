package com.example.finalproject.controller;

import com.example.finalproject.auth.GetInfo;
import com.example.finalproject.model.Image;
import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Tables;
import com.example.finalproject.model.User;
import com.example.finalproject.service.ImageService;
import com.example.finalproject.service.OrderTableService;
import com.example.finalproject.service.TableService;
import com.example.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Controller
public class AppController {

    @Autowired
    GetInfo getIn4;

    @Autowired
    TableService tableService;

    @Autowired
    ImageService imageService;

    @Autowired
    OrderTableService orderTableService;

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String layoutAdmin(
            Model model
    ) {
        getIn4.GetAllIn4(model);

        Long allUser = userService.countAllUser();
        Long allOrder = orderTableService.countAllOrder();
        Long allTable = tableService.countAllTable();

        model.addAttribute("allUser", allUser);
        model.addAttribute("allOrder", allOrder);
        model.addAttribute("allTable", allTable);

        return "admin/index";
    }

    @GetMapping({"/page/1", "/"})
    public String index(
            Model model
    ) {
        return layoutClient(model, 1);
    }

    @GetMapping({"/page/{pageNumber}"})
    public String layoutClient(
            Model model,
            @PathVariable("pageNumber") int currentPage
    ) {
        // pagination
        Page<Tables> page = tableService.listAll(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        List<Tables> tablesLists = page.getContent();
        model.addAttribute("tablesLists", tablesLists);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        List<Image> imageLists = imageService.listAll();
        model.addAttribute("imageLists", imageLists);

        getIn4.GetAllIn4(model);
        return "client/index";
    }

    @GetMapping("/404")
    public String form404() {
        return "client/404";
    }

    @GetMapping("/form_order/{id}")
    public String formOrder(
            @PathVariable("id") Long id,
            Model model
    ) {
        getIn4.GetAllIn4(model);

        Optional<Tables> tablesOptional = tableService.findById(id);

        if (tablesOptional.isPresent()) {
            // optional return 2 result
//            Image image = imageService.findImageByTableId(id);
            model.addAttribute("tables", tablesOptional.get());
//            model.addAttribute("images", image);
//            System.out.println(image);
            return "client/form_order";
        }

        return "redirect:/404";
    }

    @GetMapping("/form/{id}")
    public String form(
            @PathVariable("id") Long id,
            Model model
    ) {
        getIn4.GetAllIn4(model);

        Optional<Tables> table = tableService.findById(id);

        model.addAttribute("table", table.get());
        model.addAttribute("order", new OrderTable());
        return "client/form";
    }

    @GetMapping("/showOrder")
    public String showOrderUser(
            Model model
    ) {
        getIn4.GetAllIn4(model);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userService.findByEmail(currentPrincipalName);

        User u = user.orElseGet(User::new);

        List<OrderTable> orderTableLists = orderTableService.findOrderTableByUserID(u);

        model.addAttribute("orderTableLists", orderTableLists);

        return "client/show_order";
    }
}
