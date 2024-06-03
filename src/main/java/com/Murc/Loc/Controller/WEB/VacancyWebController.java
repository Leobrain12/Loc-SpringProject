package com.Murc.Loc.Controller.WEB;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;
import com.Murc.Loc.Specification.VacancySpecification;
import com.Murc.Loc.Service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

@Controller
@RequiredArgsConstructor
public class VacancyWebController {

    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping("/vacancies")
    public String showVacancies(
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdAfter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Specification<Vacancy> spec = Specification.where(null);

        if (experience != null && !experience.isEmpty()) {
            spec = spec.and(VacancySpecification.hasExperience(experience));
        }

        if (skill != null && !skill.isEmpty()) {
            spec = spec.and(VacancySpecification.hasSkills(skill));
        }

        if (description != null && !description.isEmpty()) {
            spec = spec.and(VacancySpecification.hasDescriptionContaining(description));
        }

        if (createdAfter != null) {
            spec = spec.and(VacancySpecification.createdAfter(createdAfter));
        }

        Page<Vacancy> vacancies = vacancyService.findVacancies(spec, pageable);
        model.addAttribute("vacancies", vacancies.getContent());
        model.addAttribute("totalPages", vacancies.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("experience", experience);
        model.addAttribute("skill", skill);
        model.addAttribute("description", description);
        model.addAttribute("createdAfter", createdAfter);

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
    @GetMapping("/vacancies/edit/{vacancyId}")
    public String showEditVacancyForm(@PathVariable Long vacancyId, Model model) {
        Vacancy vacancy = vacancyService.findById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        return "edit_vacancy";
    }
    @PostMapping("/vacancies/update/{vacancyId}")
    public String updateVacancy(
            @PathVariable Long vacancyId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("shortDescription") String shortDescription,
            @RequestParam("skills") String skills,
            @RequestParam("salary") String salary,
            @RequestParam("experience") String experience,
            @RequestParam("age") int age,
            @RequestParam("image") MultipartFile image,
            Model model) {

        Vacancy existingVacancy = vacancyService.findById(vacancyId);

        existingVacancy.setName(name);
        existingVacancy.setDescription(description);
        existingVacancy.setShortDescription(shortDescription);
        existingVacancy.setSkills(new ArrayList<>(Arrays.asList(skills.split(","))));
        existingVacancy.setSalary(salary);
        existingVacancy.setExperience(experience);
        existingVacancy.setAge(age);

        if (!image.isEmpty()) {
            String fileName = saveFile(image);
            existingVacancy.setImage(fileName);
        }

        vacancyService.updateVacancy(existingVacancy, vacancyId);

        return "redirect:/vacancies";
    }
}
