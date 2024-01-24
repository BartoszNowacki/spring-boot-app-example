package com.bartosznowacki.app.userdetailsservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// TODO: Update this to non deprecated solution
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/v1/public/**",
            "/v1/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };
    final AuthenticationFilter authenticationFilter;

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = enableCors(http);
        http = setSessionManagementToStateless(http);
        http = addJwtTokenFilter(http);
        setPermissionsOnEndpoint(http);
        return http.build();
    }

    private HttpSecurity enableCors(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable();
    }

    private HttpSecurity setSessionManagementToStateless(HttpSecurity http) throws Exception {
        return http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
    }

    private void setPermissionsOnEndpoint(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
        );
    }

    private HttpSecurity addJwtTokenFilter(HttpSecurity http) {
        return http.addFilterBefore(
                authenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
