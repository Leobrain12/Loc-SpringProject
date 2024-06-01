package com.Murc.Loc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.Murc.Loc.Model.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy, Long>, JpaSpecificationExecutor<Vacancy> {
    void deleteById(Long VacancyId);
    Vacancy findByVacancyId(Long VacancyId);
}
