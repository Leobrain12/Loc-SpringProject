package com.Murc.Loc.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Murc.Loc.Service.VacancyService;

import lombok.AllArgsConstructor;

import com.Murc.Loc.Model.*;
import com.Murc.Loc.Repository.DAO.VacancyDao;;
@Service
@AllArgsConstructor
public class UserSeekerServiceIMPL implements VacancyService {
    private final VacancyDao repository;



    @Override
    public Vacancy findById(Long Id) {
        return repository.findById(Id);
    }

    @Override
    public List<Vacancy> findAllVacancy() {
        return repository.findAllVacancy();
    }

    @Override
    public Vacancy saveVacancy(Vacancy newVacancy) {
        return repository.saveUser(newVacancy);
    }

    @Override
    public Vacancy updateVacancy(Vacancy vacancy, Long Id) {
        return repository.updateUser(vacancy, Id);
    }

    @Override
    public void deleteVacancy(Long Id) {
        repository.deleteUser(Id);
    }


}

