package com.example.finalproject.controller;

import com.example.finalproject.auth.GetInfo;
import com.example.finalproject.model.OrderTable;
import com.example.finalproject.model.Status;
import com.example.finalproject.service.OrderTableService;
import com.example.finalproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/order")
public class OrderTableController {
    @Autowired
    OrderTableService orderTableService;

    @Autowired
    StatusService statusService;

    @Autowired
    GetInfo getInfo;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<OrderTable> orderTableLists = orderTableService.findAllByOrderByIdDesc();

        model.addAttribute("title", "Order");
        model.addAttribute("orderTableLists", orderTableLists);

        getInfo.GetAllIn4(model);
        return "admin/order/index";
    }

    @GetMapping("/store")
    public String store(
            OrderTable orderTable
    ) {
        orderTableService.save(orderTable);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<OrderTable> optional = orderTableService.findById(id);

//        List<Status> statusLists = statusService.listAll();

        // lambda expression
//        optional.ifPresent(orderTable -> {
//            model.addAttribute("order", orderTable); // .get()
//            model.addAttribute("title", "Edit Order");
//        });

        if (optional.isPresent()) {
            model.addAttribute("order", optional.get());
//            model.addAttribute("statusLists", statusLists);
            model.addAttribute("title", "Edit Order");
            return "admin/order/edit";
        }

        ra.addFlashAttribute("err", "ERR");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            OrderTable newOrderTable,
            RedirectAttributes ra,
            HttpServletRequest request

            ) {
        Optional<OrderTable> optional = orderTableService.findById(id);

        if (optional.isPresent()) {
            optional.get().setUpdatedAt(newOrderTable.getUpdatedAt());
            optional.get().setStartTime(newOrderTable.getStartTime());
            optional.get().setEndTime(newOrderTable.getEndTime());
            optional.get().setNote(newOrderTable.getNote());
            orderTableService.save(optional.get());

            ra.addFlashAttribute("msg", "Ok");
            return "redirect:/admin/order";
        }

        ra.addFlashAttribute("err", "ERR");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    //
    @GetMapping("/updateStatus/{id}")
    public String updateStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<OrderTable> optionalOrder = orderTableService.findById(id);

        Optional<Status> optionalStatus = statusService.findById(2L);

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    @GetMapping("/inProgressStatus/{id}")
    public String inProgressStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<OrderTable> optionalOrder = orderTableService.findById(id);

        Optional<Status> optionalStatus = statusService.findById(4L);

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    @GetMapping("/cancelStatus/{id}")
    public String cancelStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<OrderTable> optionalOrder = orderTableService.findById(id);

        Optional<Status> optionalStatus = statusService.findById(3L);

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    private String upgradeStatus(
            HttpServletRequest request,
            RedirectAttributes ra,
            Optional<OrderTable> optionalOrder,
            Optional<Status> optionalStatus
    ) {

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setStatus(optionalStatus.get());
            orderTableService.save(optionalOrder.get());
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        ra.addFlashAttribute("err", "ERR");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
