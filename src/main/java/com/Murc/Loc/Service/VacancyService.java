package com.Murc.Loc.Service;

import com.Murc.Loc.Model.Vacancy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VacancyService {
    List<Vacancy> findAllVacancy();
    Vacancy saveVacancy(Vacancy newVacancy);
    Vacancy updateVacancy(Vacancy vacancy, Long Id);
    void deleteVacancy(Long Id);
    Vacancy findById(Long Id);
    Page<Vacancy> findVacancies(Specification<Vacancy> spec, Pageable pageable);
}
