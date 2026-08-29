package com.railway_booking.railwaybooking.security;

import com.railway_booking.railwaybooking.entity.User;
import com.railway_booking.railwaybooking.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserRepository userRepository) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            String username = jwtService.extractUsername(token);

            if (jwtService.isTokenValid(token, username)
                    && SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

                User user = userRepository
                        .findByUsername(username)
                        .orElse(null);

                if (user != null) {

                    System.out.println("JWT USERNAME = " + username);
                    System.out.println("DATABASE ROLE = " + user.getRole());

                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(
                                    "ROLE_" + user.getRole()
                            );

                    System.out.println("AUTHORITY = " + authority.getAuthority());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    List.of(authority)
                            );

                    System.out.println(
                            "AUTHENTICATION = " + authentication
                    );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                    System.out.println(
                            "SECURITY CONTEXT = "
                                    + SecurityContextHolder.getContext().getAuthentication()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println("JWT ERROR: " + e.getMessage());
            e.printStackTrace();

            SecurityContextHolder.clearContext();
        }

        System.out.println(
                "BEFORE FILTER CHAIN: "
                        + SecurityContextHolder.getContext().getAuthentication()
        );

        filterChain.doFilter(request, response);

        System.out.println(
                "AFTER FILTER CHAIN: "
                        + SecurityContextHolder.getContext().getAuthentication()
        );
    }
}