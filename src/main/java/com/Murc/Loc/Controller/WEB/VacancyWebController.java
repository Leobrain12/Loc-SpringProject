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
    public String addVacancy(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("skills") String skills,
            @RequestParam("salary") String salary,
            @RequestParam("experience") String experience,
            @RequestParam("age") int age,
            @RequestParam("image") MultipartFile image,
            Model model) {
        String fileName = null;
    
        Vacancy vacancy = new Vacancy();
        vacancy.setName(name);
        vacancy.setDescription(description);
        vacancy.setShortDescription(shortDescription);
        vacancy.setSkills(List.of(skills.split(",")));
        vacancy.setSalary(salary);
        vacancy.setExperience(experience);
        vacancy.setAge(age);
    
        if (!image.isEmpty()) {
            fileName = saveFile(image);
            vacancy.setImage(fileName);
        }
    
        vacancyService.saveVacancy(vacancy);
        return "redirect:/vacancies";
    }
    
    
    private String saveFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try {
            Path path = Paths.get("src/main/resources/static/uploads/" + fileName);
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
        return fileName;
    }

    @GetMapping("/vacancy/details/{vacancyId}")
    public String viewVacancyDetails(@PathVariable Long vacancyId, Model model) {
        Vacancy vacancy = vacancyService.findById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        return "vacancy_details";
    }
}
