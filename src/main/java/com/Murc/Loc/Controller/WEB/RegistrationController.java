package com.Murc.Loc.Controller.WEB;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Murc.Loc.Auth.RegisterRequest;
import com.Murc.Loc.Model.Role;
import com.Murc.Loc.Model.User;
import com.Murc.Loc.Service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegisterRequest registerRequest) {
        User user = User.builder()
            .firstName(registerRequest.getFirstName())
            .lastName(registerRequest.getLastName())
            .email(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .role(Role.USER)
            .build();
        userService.saveUser(user);
        return "redirect:/login";
    }
}
