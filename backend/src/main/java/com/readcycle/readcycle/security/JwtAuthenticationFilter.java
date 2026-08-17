package com.readcycle.readcycle.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("========== JWT FILTER EXECUTED ==========");
        System.out.println("Request: " + request.getMethod() + " " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        System.out.println("Authorization Header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            System.out.println("Bearer token found");

            try {
                email = jwtUtil.extractEmail(token);

                System.out.println("Email extracted from token: " + email);

            } catch (Exception e) {

                System.out.println("JWT extraction failed: " + e.getMessage());

            }
        } else {

            System.out.println("No Bearer token found");

        }

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            System.out.println("Loading user details for: " + email);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("User found: " + userDetails.getUsername());

            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                System.out.println("========== TOKEN VALID ==========");

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);

                System.out.println("Authentication set in SecurityContext");

            } else {

                System.out.println("========== TOKEN INVALID ==========");

            }
        }

        filterChain.doFilter(request, response);

        System.out.println("========== JWT FILTER FINISHED ==========");
    }
}