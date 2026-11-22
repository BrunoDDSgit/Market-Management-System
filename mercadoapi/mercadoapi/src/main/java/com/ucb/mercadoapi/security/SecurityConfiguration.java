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
    SecurtyFilter  securtyFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/", "/home", "/login", "/cadastro", "/produtos", "/usuarios", "/*.html", "/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register", "/produtos", "/usuarios", "/categorias").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/produtos/**", "/usuarios").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securtyFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
