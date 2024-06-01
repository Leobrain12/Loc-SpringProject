package com.Murc.Loc.Service.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Repository.VacancyRepository;
import com.Murc.Loc.Service.VacancyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Primary
public class InMemVacancyServiceIMPL implements VacancyService {

    private final VacancyRepository repository;

    @Override
    public List<Vacancy> findAllVacancy() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Vacancy saveVacancy(Vacancy newVacancy) {
        return repository.save(newVacancy);
    }

    @Transactional
    @Override
    public Vacancy updateVacancy(Vacancy vacancy, Long Id) {
        return repository.save(vacancy);
    }

    @Transactional
    @Override
    public void deleteVacancy(Long Id) {
        repository.deleteById(Id);
    }

    @Override
    public Vacancy findById(Long Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public Page<Vacancy> findVacancies(Specification<Vacancy> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
