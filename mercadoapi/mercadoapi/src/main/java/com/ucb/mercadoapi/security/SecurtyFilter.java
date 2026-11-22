package com.ucb.mercadoapi.security;

import com.ucb.mercadoapi.repository.UsuarioRepository;
import com.ucb.mercadoapi.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurtyFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validateToken(token);
            if (email != null && !email.isBlank()) {
                UserDetails user = usuarioRepository.findByEmail(email);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if(header == null) return null;
        return header.replace("Bearer ", "");
    }
}
