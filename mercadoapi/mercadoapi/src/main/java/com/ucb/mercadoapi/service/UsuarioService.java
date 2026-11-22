
package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.dto.UsuarioDTO;
import com.ucb.mercadoapi.entity.UsuarioEntity;
import com.ucb.mercadoapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> UsuarioDTO.builder()
                        .id(usuario.getId())
                        .nome(usuario.getNome())
                        .email(usuario.getEmail())
                        .telefone(usuario.getTelefone())
                        .cargo(usuario.getCargo().getNome())
                        .build()
                )
                .toList();
    }
}
