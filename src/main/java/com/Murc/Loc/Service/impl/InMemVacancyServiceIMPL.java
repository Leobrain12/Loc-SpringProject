package com.Murc.Loc.Service.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Repository.VacancyRepository;
import com.Murc.Loc.Service.VacancyService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
@Primary
public class InMemVacancyServiceIMPL implements VacancyService {
    private VacancyRepository repository;
    @Override
    public List<Vacancy> findAllVacancy() {
        return repository.findAll();
    }

    @Override
    public Vacancy saveVacancy(Vacancy newVacancy) {
        return repository.save(newVacancy);
    }

    @Override
    public Vacancy updateVacancy(Vacancy vacancy, Long Id) {
        return repository.save(vacancy);
    }

    @Override
    public void deleteVacancy(Long Id) {
        repository.deleteById(Id);
    }

    @Override
    public Vacancy findById(Long Id) {
        return repository.findByVacancyId(Id); 
    }
    
}
