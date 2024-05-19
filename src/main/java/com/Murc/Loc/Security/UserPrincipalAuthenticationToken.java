package com.Murc.Loc.Security;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserPrincipals userPrincipals;

    public UserPrincipalAuthenticationToken(UserPrincipals userPrincipals) {
        super(userPrincipals.getAuthorities());
        this.userPrincipals = userPrincipals;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipals getPrincipal() {
        return userPrincipals;
    }
}