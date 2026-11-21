
package com.ucb.mercadoapi.repository;

import com.ucb.mercadoapi.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
}
