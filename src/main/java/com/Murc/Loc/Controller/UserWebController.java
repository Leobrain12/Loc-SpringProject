package com.Murc.Loc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Murc.Loc.Service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserWebController {
    
    private final UserService service;


    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", service.findAllUser());
        return "users";
    }
}
