package com.devmicheletto.furia.controllers;

import com.devmicheletto.furia.dto.JogoDTO;
import com.devmicheletto.furia.services.JogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public List<JogoDTO> listar(@RequestParam(required = false) Long furiaId) {
        if (furiaId != null) {
            return jogoService.listarPorTime(furiaId);
        } else {
            return jogoService.listarTodos();
        }
    }
}

