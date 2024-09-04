package com.example.jwt.global.security.filter;

import com.example.jwt.global.security.jwt.consts.AuthConstant;
import com.example.jwt.global.security.jwt.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    protected static final Set<String> SKIP_AUTHENTICATION_URI = Set.of(
        "/auth/signup",
        "/auth/email-duplicate",
        "/auth/login",
        "/auth/refresh",
        "/auth/temp-password"
    );

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider accessTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AuthConstant.AUTH_HEADER.getValue());

        if (header == null || !header.startsWith(AuthConstant.AUTH_TOKEN_PREFIX.getValue()) || shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String payload = accessTokenProvider.resolveTokenFromHeader(header);
        authenticate(payload, request);
        filterChain.doFilter(request, response);
    }

    private void authenticate(String username, HttpServletRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return SKIP_AUTHENTICATION_URI.contains(uri);
    }
}
