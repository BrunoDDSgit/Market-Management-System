
package com.ucb.mercadoapi.config;

import com.ucb.mercadoapi.entity.*;
import com.ucb.mercadoapi.repository.*;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final StatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email:admin@mmarket.com}")
    private String emailAdmin;

    @Value("${app.admin.password}")
    private String senhaAdmin;

    @Override
    public void run(String... args) {

        cadastrarAdministrador();

    }

    private void cadastrarAdministrador() {

        if (usuarioRepository.findByEmail(emailAdmin) != null) {
            System.out.println("Administrador já cadastrado!");
            return;
        }

        CargoEntity cargoAdmin = cargoRepository.findByNome("ADMIN")
                .orElseThrow(() ->
                        new RuntimeException("Cargo ADMIN não encontrado!")
                );

        StatusEntity statusAtivo = statusRepository.findByNome("ATIVO")
                .orElseThrow(() ->
                        new RuntimeException("Status ATIVO não encontrado!")
                );

        String senhaCriptografada = passwordEncoder.encode(senhaAdmin);

        UsuarioEntity administrador = new UsuarioEntity(
                "Administrador",
                emailAdmin,
                "99999999999",
                senhaCriptografada,
                statusAtivo,
                cargoAdmin
        );

        usuarioRepository.save(administrador);

        System.out.println("Administrador cadastrado com sucesso!");
    }
}
