package com.Murc.Loc.Repository.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Murc.Loc.Model.Vacancy;

@Repository
public class VacancyDao {
    private final List<Vacancy> VACANCY = new ArrayList<>();

    public List<Vacancy> findAllVacancy() {
        return VACANCY;
    }

    
    public Vacancy saveUser(Vacancy newVacancy) {
        VACANCY.add(newVacancy);
        return newVacancy;
    }
    
    public Vacancy findById(Long VacancyId){
        return VACANCY.stream()
            .filter(element -> element.getVacancyId().equals(VacancyId))
            .findFirst()
            .orElse(null);
    }
    public Vacancy updateUser(Vacancy vacancy,Long VacancyId) {
        var vacancyIndex = -1;
        for(Vacancy CurretVacancy: VACANCY)
        {
            if(CurretVacancy.getVacancyId().equals(VacancyId))
            {
                vacancyIndex = VACANCY.indexOf(CurretVacancy);
                VACANCY.set(vacancyIndex,vacancy);
            }
        }
        return(vacancyIndex == -1) ? saveUser(vacancy) : vacancy;
    }

    
    public void deleteUser(Long VacancyId) {
        var vacancy = findById(VacancyId);
        if(vacancy != null)
        {
            VACANCY.remove(vacancy);
        }
    }
}
