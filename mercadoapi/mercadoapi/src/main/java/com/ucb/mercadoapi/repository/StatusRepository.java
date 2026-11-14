package com.ucb.mercadoapi.repository;

import com.ucb.mercadoapi.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    Optional<StatusEntity> findByNome(String nome);
}
