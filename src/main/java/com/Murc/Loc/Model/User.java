package com.Murc.Loc.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name = "oc_user")
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private String fatherName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private final Role role;
    @Column(unique = true)
    private final String email;
    private final String password;
    private final String telephone;
    private String image;
    private String description;
    private Long zoneId;
}