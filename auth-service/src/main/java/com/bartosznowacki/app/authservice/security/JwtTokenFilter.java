package com.bartosznowacki.app.authservice.security;

import com.bartosznowacki.app.authservice.jwt.IJwtService;
import com.bartosznowacki.app.authservice.token.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
class JwtTokenFilter extends OncePerRequestFilter {
    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ITokenService tokenService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull jakarta.servlet.http.HttpServletResponse response,
                                    @NonNull jakarta.servlet.FilterChain chain
    ) throws jakarta.servlet.ServletException, IOException {
        if (validateTokenHeader(request) && validateToken(request) && validateTokenInDB(request)) {
            applyAuthenticationToSecurityContext(request);
        }
        chain.doFilter(request, response);
    }

    private boolean validateTokenHeader(jakarta.servlet.http.HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .map(notNullHeader -> notNullHeader.startsWith("Bearer "))
                .orElse(false);
    }

    private boolean validateToken(jakarta.servlet.http.HttpServletRequest request) {
        final String jwt = getJwt(request);
        return jwtService.validate(jwt);
    }

    private boolean validateTokenInDB(jakarta.servlet.http.HttpServletRequest request) {
        final String jwt = getJwt(request);
        return tokenService.validateTokenInDB(jwt);
    }

    private void applyAuthenticationToSecurityContext(HttpServletRequest request) {
        final String jwt = getJwt(request);
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtService.extractUsername(jwt));
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? List.of() : userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getJwt(jakarta.servlet.http.HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .map(header -> header.split(" ")[1].trim())
                .orElse(null);
    }
}
