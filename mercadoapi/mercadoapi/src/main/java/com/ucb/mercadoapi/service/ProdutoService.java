
package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.dto.ProdutoDTO;
import com.ucb.mercadoapi.entity.CategoriaEntity;
import com.ucb.mercadoapi.entity.ProdutoEntity;
import com.ucb.mercadoapi.repository.CategoriaRepository;
import com.ucb.mercadoapi.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity findById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));
    }

    public ProdutoEntity createProduct(ProdutoDTO produtoDTO) {

        CategoriaEntity categoria = categoriaRepository
                .findById(produtoDTO.getCategoriaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        ProdutoEntity produto = new ProdutoEntity(
                produtoDTO.getNome(),
                produtoDTO.getDescricao(),
                produtoDTO.getCodigoBarras(),
                produtoDTO.getQuantidade(),
                produtoDTO.getPreco(),
                produtoDTO.getDesconto(),
                produtoDTO.getValidade(),
                categoria
        );

        return produtoRepository.save(produto);
    }

    public ProdutoEntity updateProduct(ProdutoDTO produtoDTO, Integer id) {

        ProdutoEntity produto = findById(id);

        CategoriaEntity categoria = categoriaRepository
                .findById(produtoDTO.getCategoriaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        produto.setNome(produtoDTO.getNome());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDesconto(produtoDTO.getDesconto());
        produto.setValidade(produtoDTO.getValidade());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void removerProduto(Integer id) {
        ProdutoEntity produto = findById(id);
        produtoRepository.delete(produto);
    }
}
