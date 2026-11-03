package com.ucb.mercadoapi.database.repository;

import com.ucb.mercadoapi.database.model.CargoEntity;
import com.ucb.mercadoapi.database.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICargoRepository extends JpaRepository<CargoEntity, Integer> {

}
