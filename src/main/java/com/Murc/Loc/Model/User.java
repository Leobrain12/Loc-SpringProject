package com.Murc.Loc.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "oc_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(unique = true)
    private String email;
    private String password;
    private String telephone;
    private String image;
    private String description;
    private Long zoneId;
}