
package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.dto.CategoriaDTO;
import com.ucb.mercadoapi.entity.CategoriaEntity;
import com.ucb.mercadoapi.service.CategoriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaEntity> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaEntity findById(@PathVariable Integer id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaEntity createCategory(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.createCategory(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        categoriaService.deleteCategory(id);
    }
}
