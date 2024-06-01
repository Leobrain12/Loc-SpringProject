package com.Murc.Loc.Controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;
import com.Murc.Loc.Specification.VacancySpecification;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/vacancy")
@AllArgsConstructor
public class VacancyRestApi {
    private final VacancyService vacancyService;

    @GetMapping
    public List<Vacancy> findAllVacancy() {
        return vacancyService.findAllVacancy();
    }

    @PostMapping("/save")
    public Vacancy saveVacancy(@RequestBody Vacancy newVacancy) {
        return vacancyService.saveVacancy(newVacancy);
    }

    @GetMapping("/{vacancyId}")
    public Vacancy findById(@PathVariable Long vacancyId) {
        return vacancyService.findById(vacancyId);
    }

    @PutMapping("/update/{vacancyId}")
    public Vacancy updateVacancy(@RequestBody Vacancy vacancy, @PathVariable Long vacancyId) {
        return vacancyService.updateVacancy(vacancy, vacancyId);
    }

    @DeleteMapping("/delete/{vacancyId}")
    public void deleteVacancy(@PathVariable Long vacancyId) {
        vacancyService.deleteVacancy(vacancyId);
    }

    @GetMapping("/search")
    public Page<Vacancy> searchVacancies(
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdAfter,
            Pageable pageable) {

        Specification<Vacancy> spec = Specification.where(null);

        if (experience != null && !experience.isEmpty()) {
            spec = spec.and(VacancySpecification.hasExperience(experience));
        }

        if (skill != null && !skill.isEmpty()) {
            for (String singleSkill : skill.split(",")) {
                spec = spec.and(VacancySpecification.hasSkills(singleSkill.trim()));
            }
        }

        if (description != null && !description.isEmpty()) {
            spec = spec.and(VacancySpecification.hasDescriptionContaining(description));
        }

        if (createdAfter != null) {
            spec = spec.and(VacancySpecification.createdAfter(createdAfter));
        }

        return vacancyService.findVacancies(spec, pageable);
    }
}
