package com.Murc.Loc.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Repository.UserRepository;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return email -> {
            User user = userRepository.findByEmail(email);
            if(user != null) return user;

            throw new UsernameNotFoundException("User '" + email + "' not found");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/api/**").hasRole("ADMIN")
            .requestMatchers("/", "/**").permitAll()
        )
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
        )
        .build();
    }
}
