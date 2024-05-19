package com.Murc.Loc.Security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final JwtProparties proparties;

    public DecodedJWT decode(String token)
    {
        return JWT.require(Algorithm.HMAC256(proparties.getSecretKey()))
                .build()
                .verify(token);
    }
}
