package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.database.model.UsuarioEntity;
import com.ucb.mercadoapi.database.repository.IUsuarioRepository;
import com.ucb.mercadoapi.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();

        return entidades.stream()
                .map(usuario -> UsuarioDTO.builder()
                        .nome(usuario.getNome())
                        .email(usuario.getEmail())
                        .telefone(usuario.getTelefone())
                        .senha(usuario.getSenha())
                        .statusUsuario(usuario.getStatusUsuario())
                        .dataCadastro(usuario.getDataCadastro())
                        .build())
                .toList();
    }

    public void save(UsuarioDTO usuarioDTO) {
        usuarioRepository.save(UsuarioEntity.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .telefone(usuarioDTO.getTelefone())
                .senha(usuarioDTO.getSenha())
                .statusUsuario(usuarioDTO.getStatusUsuario())
                .dataCadastro(usuarioDTO.getDataCadastro())
                .build());
    }

    //validando DTOS 48:00
    //fazer validações

    //fazer tratamento de erro quando body = null

}
