package com.Murc.Loc.Model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "oc_vacancy_to_company")
@Data
public class VacancyToCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Long vacancyId;

    private Long companyId;

    @ManyToOne
    @JoinColumn( insertable = false, updatable = false)
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Company company;
}