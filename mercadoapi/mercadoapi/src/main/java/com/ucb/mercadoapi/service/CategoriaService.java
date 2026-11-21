
package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.dto.CategoriaDTO;
import com.ucb.mercadoapi.entity.CategoriaEntity;
import com.ucb.mercadoapi.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    public CategoriaEntity findById(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));
    }

    public CategoriaEntity createCategory(CategoriaDTO categoriaDTO) {

        CategoriaEntity categoria = new CategoriaEntity(
                categoriaDTO.getNome(),
                categoriaDTO.getDescricao()
        );

        return categoriaRepository.save(categoria);
    }

    public CategoriaEntity updateCategory(CategoriaDTO categoriaDTO, Integer id) {

        CategoriaEntity categoria = findById(id);

        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public void deleteCategory(Integer id) {
        CategoriaEntity categoria = findById(id);
        categoriaRepository.delete(categoria);
    }
}
