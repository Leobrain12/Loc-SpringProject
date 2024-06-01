package com.Murc.Loc.Controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;

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
}
