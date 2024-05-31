package com.Murc.Loc.Controller.api;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;
import com.Murc.Loc.Service.UserService;
import com.Murc.Loc.Model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/vacancy")
@AllArgsConstructor
public class VacancyRestApi {
    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping
    public List<Vacancy> findAllVacancy() {
        return vacancyService.findAllVacancy();
    }

    @PostMapping("save")
    public Vacancy saveVacancy(@RequestBody Vacancy newVacancy) {
        return vacancyService.saveVacancy(newVacancy);
    }

    @GetMapping("/{vacancyId}")
    public Vacancy findById(@PathVariable Long vacancyId) {
        return vacancyService.findById(vacancyId);
    }

    @PutMapping("update/{vacancyId}")
    public Vacancy updateVacancy(@RequestBody Vacancy vacancy, @PathVariable Long vacancyId) {
        return vacancyService.updateVacancy(vacancy, vacancyId);
    }

    @DeleteMapping("delete/{vacancyId}")
    public void deleteVacancy(@PathVariable Long vacancyId) {
        vacancyService.deleteVacancy(vacancyId);
    }

    @PostMapping("/favorite/{vacancyId}")
    public void addFavorite(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        userService.addFavoriteVacancy(user, vacancyId);
    }

    @GetMapping("/favorites")
    public Set<Vacancy> getFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteVacancies();
    }
}
