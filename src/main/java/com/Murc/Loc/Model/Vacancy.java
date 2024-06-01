package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "oc_vacancy")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Long vacancyId;
    private String name;
    private String image;
    private String description;
    private Long zoneId;
    @ElementCollection
    private List<String> skills;
    private String salary;
    private String experience;
    private int age;
}
