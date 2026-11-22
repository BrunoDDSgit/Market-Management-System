
package com.ucb.mercadoapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurtyFilter securtyFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(
                "/",
                "/login",
                "/cadastro",
                "/login.html",
                "/cadastro.html",
                "/index.html",
                "/home",
                "/css/**",
                "/js/**",
                "/img/**",
                "/favicon.ico"
            ).permitAll()

            .requestMatchers(
                    HttpMethod.POST,
                    "/auth/login",
                    "/auth/register"
            ).permitAll()

            .requestMatchers(
                    HttpMethod.GET,
                    "/produtos",
                    "/produtos/**",
                    "/categorias",
                    "/categorias/**"
            ).hasRole("USER")

            .requestMatchers(
                    HttpMethod.PUT,
                    "/produtos/**"
            ).hasRole("USER")

            .requestMatchers(
                    HttpMethod.POST,
                    "/produtos",
                    "/produtos/**"
            ).hasRole("ADMIN")

            .requestMatchers(
                    HttpMethod.DELETE,
                    "/produtos",
                    "/produtos/**"
            ).hasRole("ADMIN")

            .requestMatchers("/usuarios", "/usuarios/**")
            .hasRole("ADMIN")

            .requestMatchers(
                    HttpMethod.POST,
                    "/categorias",
                    "/categorias/**"
            ).hasRole("ADMIN")

            .requestMatchers(
                    HttpMethod.PUT,
                    "/categorias/**"
            ).hasRole("ADMIN")

            .requestMatchers(
                    HttpMethod.DELETE,
                    "/categorias/**"
            ).hasRole("ADMIN")

            .requestMatchers("/historico/**").hasRole("ADMIN")

            .anyRequest().authenticated()
    )

            .addFilterBefore(
                    securtyFilter,
                    UsernamePasswordAuthenticationFilter.class
            )

            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
