package com.faz.demo.spring.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final String secret;

    JwtAuthorizationFilter(AuthenticationManager authenticationManager, String secret) {
        super(authenticationManager);
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authToken = request.getHeader("Authorization");

        if (Objects.nonNull(authToken) && authToken.startsWith("Bearer ")) {
            String jwt = authToken.replaceAll("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
            String username = claims.getSubject();

            if (Objects.nonNull(username)) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", getAuthorities(claims));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> getAuthorities(Claims claims) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("authorities", String.class));
    }
}