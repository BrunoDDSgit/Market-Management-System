package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.entity.ProdutoEntity;
import com.ucb.mercadoapi.dto.ProdutoDTO;
import com.ucb.mercadoapi.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<ProdutoEntity>();

    public List<ProdutoEntity> findAll() {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoEntity createProduct(ProdutoDTO produtoDTO) {

        Integer identificador = PRODUTOS.stream().mapToInt(ProdutoEntity::getId).max().orElse(0)+1;

        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .id(identificador)
                .nome(produtoDTO.getNome())
                .valor(produtoDTO.getValor())
                .quantidade(produtoDTO.getQuantidade())
                .build();

        PRODUTOS.add(novoProduto);

        return novoProduto;
    }

    public ProdutoEntity updateProduct(ProdutoDTO produtoDTO, Integer id) throws NotFoundException {
        ProdutoEntity produto = PRODUTOS.stream()
                .filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("Produto não Encontrado"));

        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setQuantidade(produtoDTO.getQuantidade());

        return produto;
    }

    public void removerProduto(Integer id) {
        PRODUTOS.removeIf(p -> p.getId().equals(id));
    }
}
