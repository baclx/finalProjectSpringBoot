package com.example.finalproject.auth;

import com.example.finalproject.model.Role;
import com.example.finalproject.model.User;
import com.example.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Optional;

@Component
public class GetInfo {
    @Autowired
    UserService userService;

    public void GetAllIn4(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userService.findByEmail(currentPrincipalName);
        User u;

        // functional style expression
        u = user.orElseGet(User::new);

        model.addAttribute("userID", u.getId());
        model.addAttribute("username", u.getUsername());

        for (Role role : u.getRoles()) {
            model.addAttribute("role", role.getName());
        }
    }
}
