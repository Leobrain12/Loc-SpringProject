package com.Murc.Loc.Service.impl;

import java.util.ArrayList;
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
    public Vacancy updateVacancy( Vacancy updatedVacancy, Long vacancyId) {
        Vacancy existingVacancy = repository.findById(vacancyId).orElseThrow(() -> new RuntimeException("Vacancy not found"));

        existingVacancy.setName(updatedVacancy.getName());
        existingVacancy.setDescription(updatedVacancy.getDescription());
        existingVacancy.setShortDescription(updatedVacancy.getShortDescription());
        existingVacancy.setSkills(new ArrayList<>(updatedVacancy.getSkills()));
        existingVacancy.setSalary(updatedVacancy.getSalary());
        existingVacancy.setExperience(updatedVacancy.getExperience());
        existingVacancy.setAge(updatedVacancy.getAge());
        existingVacancy.setImage(updatedVacancy.getImage());

        return repository.save(existingVacancy);
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
