package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.CategoriaEntity;
import com.ucb.mercadoapi.database.model.ProdutoVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

}
