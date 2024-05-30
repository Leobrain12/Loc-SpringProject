package com.Murc.Loc.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Murc.Loc.Model.Role;
import com.Murc.Loc.Model.User;
import com.Murc.Loc.Repository.UserRepository;
import com.Murc.Loc.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Пароль хэшируется
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        logger.info("User {} registered successfully with token {}", user.getEmail(), jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            logger.debug("Attempting to authenticate user {}", request.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var userDetails = (UserDetails) authentication.getPrincipal();
            var jwtToken = jwtService.generateToken(userDetails);
            logger.info("User {} authenticated successfully with token {}", request.getEmail(), jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            logger.error("Authentication failed for user {}. Error: {}", request.getEmail(), e);
            throw new RuntimeException("Invalid email or password");
        }
    }
}
