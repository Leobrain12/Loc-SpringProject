package com.Murc.Loc.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Data
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProparties {
    private String secretKey;
    private long tokenExpirationAfterDays;
}
