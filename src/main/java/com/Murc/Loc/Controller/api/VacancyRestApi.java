package com.Murc.Loc.Controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Service.VacancyService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/vacancy")
@AllArgsConstructor
public class VacancyRestApi {
    private final VacancyService service;
    @GetMapping
    public List<Vacancy> findAllVacancy(){
        return service.findAllVacancy();
    }
    @PostMapping("save")
    public Vacancy saveUser(@RequestBody Vacancy newVacancy){
        return service.saveVacancy(newVacancy);
    }
    @GetMapping("/{vacancyId}")
    public Vacancy findById(@PathVariable Long vacancyId){
        return service.findById(vacancyId);
    }
    @PutMapping("update/{vacancyId}")
    public Vacancy updateUser(@RequestBody Vacancy vacancy, @PathVariable Long vacancyId){
        return service.updateVacancy(vacancy, vacancyId);
    }
    @DeleteMapping("delete/{vacancyId}")
    public void deleteVacancy(@PathVariable Long vacancyId){
        service.deleteVacancy(vacancyId);
    }
}

