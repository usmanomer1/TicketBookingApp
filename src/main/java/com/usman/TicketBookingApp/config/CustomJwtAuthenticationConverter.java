package com.usman.TicketBookingApp.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public CustomJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("ROLE_");  // Ensure roles have the 'ROLE_' prefix
        converter.setAuthoritiesClaimName("roles");  // Use 'roles' claim to fetch authorities
        this.setJwtGrantedAuthoritiesConverter(converter);
    }


    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> authorities = super.convert(jwt).getAuthorities();
        // You can add additional custom logic for authorities here
        return authorities;
    }
}
