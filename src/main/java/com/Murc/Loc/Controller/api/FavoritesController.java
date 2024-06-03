package com.Murc.Loc.Controller.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.Murc.Loc.Service.UserService;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Model.User;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor
public class FavoritesController {
    private final UserService userService;

    @PostMapping("/add/{vacancyId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        userService.addFavoriteVacancy(user, vacancyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{vacancyId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        userService.removeFavoriteVacancy(user, vacancyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Vacancy>> getFavoriteVacancies(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(userService.getFavoriteVacancies(user));
    }
}
