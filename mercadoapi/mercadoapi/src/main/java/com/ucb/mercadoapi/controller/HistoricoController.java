package com.ucb.mercadoapi.controller;

import com.ucb.mercadoapi.entity.HistoricoEntity;
import com.ucb.mercadoapi.repository.HistoricoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoRepository historicoRepository;

    @GetMapping
    public List<HistoricoEntity> listarHistorico() {
        return historicoRepository.findAllByOrderByHorarioDesc();
    }
}