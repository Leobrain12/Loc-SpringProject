package com.Murc.Loc.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Murc.Loc.Security.UserPrincipals;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username);
        return UserPrincipals.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .password(user.getPassword())
                .build();
    }
}
