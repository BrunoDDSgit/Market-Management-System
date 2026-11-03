package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}
