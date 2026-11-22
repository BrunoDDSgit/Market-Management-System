package com.ucb.mercadoapi.repository;

import com.ucb.mercadoapi.entity.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Integer> {

    List<HistoricoEntity> findAllByOrderByHorarioDesc();
}