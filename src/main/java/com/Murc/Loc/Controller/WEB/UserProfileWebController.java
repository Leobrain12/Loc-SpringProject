package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserProfileWebController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String telephone,
            Model model) {

        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTelephone(telephone);

        userService.updateUser(user, user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("successMessage", "Profile updated successfully");
        return "profile";
    }

    @GetMapping("/profile/security")
    public String getSecurityPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "security";
    }

    @PostMapping("/profile/security/update")
    public String updateSecurity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            @RequestParam String newEmail,
            @RequestParam String confirmEmail,
            Model model) {

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match");
            return "security";
        }

        if (!newEmail.equals(confirmEmail)) {
            model.addAttribute("errorMessage", "Emails do not match");
            return "security";
        }

        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(newPassword);
        user.setEmail(newEmail);

        userService.updateUser(user, user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("successMessage", "Security settings updated successfully");
        return "security";
    }

    @GetMapping("/profile/favorites")
    public String getFavoriteVacancies(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<Vacancy> favoriteVacancies = user.getFavoriteVacancies();
        model.addAttribute("favoriteVacancies", favoriteVacancies);
        return "favorite_vacancies";
    }
}


