package com.Murc.Loc.Controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Murc.Loc.Model.Role;
import com.Murc.Loc.Model.Config.LoginRequest;
import com.Murc.Loc.Model.Config.LoginResponse;
import com.Murc.Loc.Security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import com.Murc.Loc.Security.UserPrincipals;
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticateManager;
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        var authentication = authenticateManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipals)authentication.getPrincipal();
        var role = principal.getAuthorities().stream().findFirst().get().getAuthority();
        var token = jwtIssuer.issue(principal.getUserId(), principal.getUsername(), Role.valueOf(role));
        return LoginResponse.builder()
        .accessToken(token)
        .build();
    }
}
