package com.Murc.Loc.Security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.Murc.Loc.Model.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProparties proparties;
    public String issue(long userId, String email, Role role) {
        
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e", email)
                .withClaim("a", role.name())
                .sign(Algorithm.HMAC256(proparties.getSecretKey()));
    }
}
