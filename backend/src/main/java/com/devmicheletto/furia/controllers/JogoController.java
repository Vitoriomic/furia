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
    public List<JogoDTO> listarJogos() {
        return jogoService.listarTodos();
    }
}
