package com.ucb.mercadoapi.repository;

import com.ucb.mercadoapi.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<CargoEntity, Integer> {
    Optional<CargoEntity> findByNome(String nome);
}
