package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

}
