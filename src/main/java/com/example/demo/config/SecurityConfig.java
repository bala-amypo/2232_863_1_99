package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (needed for Swagger & POST requests)
            .csrf(csrf -> csrf.disable())

            // Disable default login page
            .formLogin(form -> form.disable())

            // Disable basic auth
            .httpBasic(basic -> basic.disable())

            // Allow everything (no authentication)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
