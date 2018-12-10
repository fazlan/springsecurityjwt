package com.faz.demo.spring.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faz.demo.spring.security.user.User;
import com.faz.demo.spring.security.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final String secret;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, String secret) {
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.secret = secret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login login = new ObjectMapper().readValue(request.getInputStream(), Login.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());
            return getAuthenticationManager().authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = userService.get(authResult.getName());
        ZonedDateTime expirationDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(15);
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(secret))
                .setSubject(user.getEmail())
                .addClaims(getClaims(user))
                .setExpiration(Date.from(expirationDateTime.toInstant()))
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }

    private Claims getClaims(User user) {
        Claims claims = new DefaultClaims();
        claims.put("firstName", user.getFirstname());
        claims.put("lastName", user.getLastname());
        claims.put("authorities", String.join(",", user.getAuthorities()));
        return claims;
    }
}
