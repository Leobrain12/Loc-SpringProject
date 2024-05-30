package com.Murc.Loc.Service;
import java.util.List;

import com.Murc.Loc.Model.Vacancy;


public interface VacancyService {
    List<Vacancy> findAllVacancy();
    Vacancy saveVacancy(Vacancy newVacancy);
    Vacancy updateVacancy(Vacancy vacancy, Long Id);
    void deleteVacancy(Long Id);
    Vacancy findById(Long Id);
}
