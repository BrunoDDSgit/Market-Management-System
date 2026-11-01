package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.database.model.ProdutoEntity;
import com.ucb.mercadoapi.dto.ProdutoDTO;
import com.ucb.mercadoapi.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoEntity> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity createProduct(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.createProduct(produtoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity updateProduct(@PathVariable("id") Integer id,
                                       @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.updateProduct(produtoDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer id) {
        produtoService.removerProduto(id);
    }
}
