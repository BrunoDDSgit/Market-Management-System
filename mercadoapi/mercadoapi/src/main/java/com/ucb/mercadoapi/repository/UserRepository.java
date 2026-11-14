package com.ucb.mercadoapi.repository;

import com.ucb.mercadoapi.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface UserRepository extends JpaRepository<UsuarioEntity, Integer> {
    @EntityGraph(attributePaths = "cargo")
    UsuarioEntity findByEmail(String email);
}
