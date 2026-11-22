
package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.entity.HistoricoEntity;
import com.ucb.mercadoapi.entity.UsuarioEntity;
import com.ucb.mercadoapi.repository.HistoricoRepository;
import com.ucb.mercadoapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;

    public void registrarAlteracao(String tipoAcao, String mensagem) {

        Authentication autenticacao =
                SecurityContextHolder.getContext().getAuthentication();

        if (autenticacao == null || !autenticacao.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado!");
        }

        UsuarioEntity usuario = usuarioRepository.findByEmail(
                autenticacao.getName()
        );

        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        HistoricoEntity historico = new HistoricoEntity();

        historico.setHorario(LocalDateTime.now());
        historico.setTipoAcao(tipoAcao);
        historico.setUsuario(usuario.getNome());
        historico.setCargo(usuario.getCargo().getNome());
        historico.setMensagem(mensagem);

        historicoRepository.save(historico);
    }
}
