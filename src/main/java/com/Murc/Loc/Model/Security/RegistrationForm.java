package com.Murc.Loc.Model.Security;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Murc.Loc.Model.Role;
import com.Murc.Loc.Model.User;

@Data
public class RegistrationForm {
    private Long userId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Role role;
    private String email;
    private String password;
    private String telephone;
    private String image;
    private String description;
    private Long zoneId;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(userId, firstName, lastName, role, email, passwordEncoder.encode(password), telephone);

    }
}
