package com.Murc.Loc.Specification;

import com.Murc.Loc.Model.Vacancy;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class VacancySpecification {

    public static Specification<Vacancy> hasExperience(String experience) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.equal(vacancy.get("experience"), experience);
    }

    public static Specification<Vacancy> hasSkills(String skill) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(vacancy.join("skills")), "%" + skill.toLowerCase() + "%");
    }

    public static Specification<Vacancy> hasDescriptionContaining(String description) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(vacancy.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Vacancy> createdAfter(Date date) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(vacancy.get("createdDate"), date);
    }
}
