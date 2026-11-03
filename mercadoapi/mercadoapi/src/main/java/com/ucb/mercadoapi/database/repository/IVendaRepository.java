package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.ProdutoEntity;
import com.ucb.mercadoapi.database.model.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendaRepository extends JpaRepository<VendaEntity, Integer> {

}
