package com.Murc.Loc.Controller.WEB;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserWebController {
    
    private final UserService service;



    @GetMapping("/users")
    public String getUsersPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String currentUserEmail = userDetails.getUsername();
        List<User> users = service.findAllUsersExcept(currentUserEmail);
        model.addAttribute("users", users);
        return "users";
    }
}
