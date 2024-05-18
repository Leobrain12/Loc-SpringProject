package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "oc_vacancy")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Long vacancyId;
    private String name;
    private String image;
    private String description;
    private Long zoneId;
}
