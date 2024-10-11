package com.metropolitan.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/register")  ||
                request.getServletPath().contains("/api/salon/get") ||
                request.getServletPath().contains("/api/vozilo/get") ||
                request.getServletPath().contains("/api/automobil/get")
        ) {
            filterChain.doFilter(request, response);
        }else{
            String header = request.getHeader("Authorization");
            if(header != null && header.startsWith("Bearer ")) {
                try{
                    String token = header.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secretKey".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT jwt = verifier.verify(token);
                    String[] roles = jwt.getClaim("uloga").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
                    authority.add(new SimpleGrantedAuthority(roles[0]));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authority);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }catch(Exception exception){
                    response.setHeader("Error", exception.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    Map<String,String> error = new HashMap<>();
                    error.put("error",exception.getMessage());
                    response.setContentType("application/json");
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            }
            else{
                filterChain.doFilter(request, response);
            }
        }
    }
}