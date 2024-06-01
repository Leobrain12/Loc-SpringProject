package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;
import com.Murc.Loc.Service.UserService;
import lombok.RequiredArgsConstructor;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;


@Controller
@RequiredArgsConstructor
public class VacancyWebController {
    private static final Logger logger = LoggerFactory.getLogger(VacancyWebController.class);

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

    @GetMapping("/vacancies/add")
    public String showAddVacancyForm() {
        return "add_vacancy";
    }

    @PostMapping("/vacancies/add")
    public String addVacancy(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("skills") String skills,
                            @RequestParam("image") MultipartFile image) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(name);
        vacancy.setDescription(description);
        vacancy.setSkills(List.of(skills.split(",")));

        if (!image.isEmpty()) {
            try {
                String uploadsDir = "src/main/resources/static/uploads/";
                Path uploadsPath = Paths.get(uploadsDir);
                if (!Files.exists(uploadsPath)) {
                    Files.createDirectories(uploadsPath);
                }

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path filePath = uploadsPath.resolve(fileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                vacancy.setImage(fileName);
                logger.info("Image uploaded successfully: {}", filePath);
            } catch (IOException e) {
                logger.error("Error uploading image", e);
                return "redirect:/vacancies/add?error";
            }
        }

        vacancyService.saveVacancy(vacancy);
        return "redirect:/vacancies";
    }
}
