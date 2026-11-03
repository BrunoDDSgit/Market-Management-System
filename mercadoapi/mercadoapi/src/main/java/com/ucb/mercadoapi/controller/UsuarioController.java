package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.database.model.UsuarioEntity;
import com.ucb.mercadoapi.dto.UsuarioDTO;
import com.ucb.mercadoapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.save(usuarioDTO);
    }

   @GetMapping("/cargos/{cargo}")
   @ResponseStatus(HttpStatus.OK)
   public List<UsuarioEntity> findAllByCargo(@RequestParam(value = "cargo") String cargo) {
        return usuarioService.findAllByCargo(cargo);
   }

}
