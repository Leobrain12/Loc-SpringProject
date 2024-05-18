package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oc_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String slogan;
    private String description;
    private Integer inn;
    private String companyName;
    private String image;
    private Long zoneId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

