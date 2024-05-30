package com.Murc.Loc.Controller.WEB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Murc.Loc.Service.UserService;

@Controller
public class UserWebController {
    
    private final UserService service;

    public UserWebController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", service.findAllUser());
        return "users";
    }
}
