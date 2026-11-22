
package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.dto.UsuarioDTO;
import com.ucb.mercadoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
