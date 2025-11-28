package com.example.Pilates.configuration;

import com.example.Pilates.service.FelhasznaloService;
import com.example.Pilates.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final FelhasznaloService userService;

    public JwtFilter(TokenService tokenService, FelhasznaloService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }
    //Authorization: Bearer <token>
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        final String username = tokenService.extractUsername(token);

        if(username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService
                    .getUserDetailService()
                    .loadUserByUsername(username);

            if (tokenService.validateToken(token, userDetails)) {
                SecurityContext context =  SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
