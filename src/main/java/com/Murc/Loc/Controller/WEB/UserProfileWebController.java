package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserProfileWebController {

    private final UserService userService;

    @GetMapping("/profile/{userId}")
    public String getUserProfile(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "profile";
    }
}
