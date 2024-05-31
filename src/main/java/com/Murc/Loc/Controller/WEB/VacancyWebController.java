package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.UserService;
import com.Murc.Loc.Service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class VacancyWebController {

    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping("/vacancies")
    public String showVacancies(Model model) {
        List<Vacancy> vacancies = vacancyService.findAllVacancy();
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }

    @PostMapping("/vacancies/favorite/{vacancyId}")
    public String addToFavorites(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        userService.addFavoriteVacancy(user, vacancyId);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/favorites")
    public String showFavoriteVacancies(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Vacancy> favoriteVacancies = user.getFavoriteVacancies();
        model.addAttribute("favoriteVacancies", favoriteVacancies);
        return "favorite_vacancies";
    }
}
