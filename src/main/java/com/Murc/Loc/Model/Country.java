package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "oc_country")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    private String name;
    private String isoCode2;
    private String isoCode3;
    private Integer status;

}
