package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "oc_zone")
@Data
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Long id;
    private String name;
    private String code;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
}

