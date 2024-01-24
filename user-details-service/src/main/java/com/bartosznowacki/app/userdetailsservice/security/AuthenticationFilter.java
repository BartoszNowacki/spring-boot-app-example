package com.bartosznowacki.app.userdetailsservice.security;

import com.bartosznowacki.app.userdetailsservice.security.auth.IAuthService;
import com.bartosznowacki.app.userdetailsservice.shared.LoggedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
class AuthenticationFilter extends OncePerRequestFilter {
    private final IAuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull jakarta.servlet.http.HttpServletResponse response,
                                    @NonNull jakarta.servlet.FilterChain chain
    ) throws jakarta.servlet.ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null) {
            final LoggedUserDto user = authService.authenticate(authorization);
            if (user != null) {
                applyAuthenticationToSecurityContext(request, user);
            }
        }
        chain.doFilter(request, response);
    }

    private void applyAuthenticationToSecurityContext(HttpServletRequest request, LoggedUserDto user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user == null ? List.of() : user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
