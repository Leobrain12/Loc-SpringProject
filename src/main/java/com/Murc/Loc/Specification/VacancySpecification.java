package com.Murc.Loc.Specification;

import com.Murc.Loc.Model.Vacancy;
import org.springframework.data.jpa.domain.Specification;

public class VacancySpecification {

    public static Specification<Vacancy> hasExperience(String experience) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.equal(vacancy.get("experience"), experience);
    }

    public static Specification<Vacancy> hasSkills(String skill) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.isMember(skill, vacancy.get("skills"));
    }

    public static Specification<Vacancy> hasDescriptionContaining(String description) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(vacancy.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Vacancy> createdAfter(java.util.Date date) {
        return (vacancy, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(vacancy.get("createdDate"), date);
    }
}
