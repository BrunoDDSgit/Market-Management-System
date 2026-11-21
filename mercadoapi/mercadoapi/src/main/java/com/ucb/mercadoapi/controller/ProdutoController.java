
package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.dto.ProdutoDTO;
import com.ucb.mercadoapi.entity.ProdutoEntity;
import com.ucb.mercadoapi.service.ProdutoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoEntity> findAll() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoEntity findById(@PathVariable Integer id) {
        return produtoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity createProduct(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.createProduct(produtoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        produtoService.removerProduto(id);
    }
}
