package com.devmicheletto.furia.controllers;

import com.devmicheletto.furia.dto.VotoDTO;
import com.devmicheletto.furia.enums.OpcaoVoto;
import com.devmicheletto.furia.services.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/{idJogo}")
    public ResponseEntity<Void> votar(@PathVariable Long idJogo,
                                      @RequestParam OpcaoVoto opcao,
                                      Authentication authentication) {
        String email = authentication.getName();
        votoService.votar(idJogo, email, opcao);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idJogo}/resultado")
    public ResponseEntity<VotoDTO> resultado(@PathVariable Long idJogo) {
        return ResponseEntity.ok(votoService.calcularTermometro(idJogo));
    }
}
