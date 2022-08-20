package com.example.finalproject.auth;

import com.example.finalproject.auth.common.Const;
import com.example.finalproject.model.Role;
import com.example.finalproject.model.User;
import com.example.finalproject.service.RoleService;
import com.example.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    UserService usersService;

    @Autowired
    RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(
            Model model
    ) {
        model.addAttribute("user", new User());

        return "auth/register";
    }

    @PostMapping("/process_register")
    public String processRegister(
            User user,
            RedirectAttributes ra
    ) {
        if (usersService.existsByEmail(user.getEmail())) {
            ra.addFlashAttribute("notification", "Email da ton tai");
            return "redirect:/register";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPass = encoder.encode(user.getPassword());
            user.setPassword(encodedPass);

            Set<Role> roles = new HashSet<>();
            Role role = roleService.findByName(Const.ROLE_USER).get();

            roles.add(role);
            user.setRoles(roles);

            usersService.save(user);
        }

        return "redirect:/login";

    }

//    @GetMapping("/sendMail")
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail() {
//        mailSenderService.sendEmail(
//                "buck110503@gmail.com",
//                "This is title to TripleDot TechWiz 2022",
//                "This is body to TripleDot TechWiz 2022"
//        );
//
////        return "redirect:/ok";
//    }
}
