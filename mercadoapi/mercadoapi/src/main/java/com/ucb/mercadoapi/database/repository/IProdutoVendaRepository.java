package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.ProdutoVendaEntity;
import com.ucb.mercadoapi.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoVendaRepository extends JpaRepository<ProdutoVendaEntity, Integer> {

}
