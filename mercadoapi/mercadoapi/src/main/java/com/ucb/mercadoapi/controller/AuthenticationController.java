package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.dto.AuthenticationDTO;
import com.ucb.mercadoapi.dto.LoginResponseDTO;
import com.ucb.mercadoapi.dto.RegisterDTO;
import com.ucb.mercadoapi.entity.CargoEntity;
import com.ucb.mercadoapi.entity.StatusEntity;
import com.ucb.mercadoapi.entity.UsuarioEntity;
import com.ucb.mercadoapi.repository.CargoRepository;
import com.ucb.mercadoapi.repository.StatusRepository;
import com.ucb.mercadoapi.repository.UsuarioRepository;
import com.ucb.mercadoapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth =  authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterDTO data) {

        if (repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        CargoEntity cargo = cargoRepository.findByNome("USER")
                .orElseThrow(() -> new IllegalStateException("Cargo USER não cadastrado"));
        StatusEntity status = statusRepository.findByNome("ATIVO")
                .orElseThrow(() -> new IllegalStateException("Status ATIVO não cadastrado"));

        String encryptedPassword = passwordEncoder.encode(data.password());
        UsuarioEntity usuario = new UsuarioEntity(
                data.nome(), data.email(), data.telefone(), encryptedPassword, status, cargo);

        this.repository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> usuarioLogado(Authentication authentication) {

        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        return ResponseEntity.ok(Map.of(
                "nome", usuario.getNome(),
                "email", usuario.getEmail(),
                "cargo", usuario.getCargo().getNome()
        ));
    }
}
