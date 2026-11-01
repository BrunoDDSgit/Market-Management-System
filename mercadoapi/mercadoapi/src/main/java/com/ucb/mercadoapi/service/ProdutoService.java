package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.database.model.ProdutoEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoEntity> produtos = List.of(
            ProdutoEntity.builder()
                    .id(1)
                    .nome("Celular")
                    .valor(new BigDecimal("5000.00"))
                    .quantidade(10)
                    .build(),

            ProdutoEntity.builder()
                    .id(2)
                    .nome("Notebook")
                    .valor(new BigDecimal("7500.00"))
                    .quantidade(5)
                    .build(),

            ProdutoEntity.builder()
                    .id(3)
                    .nome("Monitor 27'")
                    .valor(new BigDecimal("1800.00"))
                    .quantidade(8)
                    .build(),

            ProdutoEntity.builder()
                    .id(4)
                    .nome("Teclado Mecânico")
                    .valor(new BigDecimal("350.00"))
                    .quantidade(15)
                    .build(),

            ProdutoEntity.builder()
                    .id(5)
                    .nome("Mouse Sem Fio")
                    .valor(new BigDecimal("150.00"))
                    .quantidade(25)
                    .build(),

            ProdutoEntity.builder()
                    .id(6)
                    .nome("Fone de Ouvido Bluetooth")
                    .valor(new BigDecimal("450.00"))
                    .quantidade(12)
                    .build(),

            ProdutoEntity.builder()
                    .id(7)
                    .nome("Cadeira Ergonômica")
                    .valor(new BigDecimal("1200.00"))
                    .quantidade(4)
                    .build(),

            ProdutoEntity.builder()
                    .id(8)
                    .nome("Mesa Stand-Desk")
                    .valor(new BigDecimal("2100.00"))
                    .quantidade(3)
                    .build(),

            ProdutoEntity.builder()
                    .id(9)
                    .nome("Webcam Full HD")
                    .valor(new BigDecimal("280.00"))
                    .quantidade(18)
                    .build(),

            ProdutoEntity.builder()
                    .id(10)
                    .nome("Carregador Indutivo")
                    .valor(new BigDecimal("120.00"))
                    .quantidade(30)
                    .build()
    );

    public List<ProdutoEntity> findAll() {
        return new ArrayList<>(produtos);
    }
}
