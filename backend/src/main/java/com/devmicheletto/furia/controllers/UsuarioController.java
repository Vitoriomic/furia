package com.devmicheletto.furia.controllers;

import com.devmicheletto.furia.dto.EstadoRankingDTO;
import com.devmicheletto.furia.dto.UsuarioDTO;
import com.devmicheletto.furia.dto.UsuarioPublicoDTO;
import com.devmicheletto.furia.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.criarUsuario(dto));
    }

    @GetMapping("/publico")
    public ResponseEntity<List<UsuarioPublicoDTO>> listarPublico() {
        return ResponseEntity.ok(usuarioService.listarPublicamente());
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> buscarProprioUsuario(Authentication authentication) {
        String email = authentication.getName(); // email vem do principal do token
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioDTO> atualizarProprioUsuario(Authentication authentication,
                                                              @RequestBody UsuarioDTO dto) {
        String email = authentication.getName();
        return ResponseEntity.ok(usuarioService.atualizarPorEmail(email, dto));
    }

    @GetMapping("/ranking-estados")
    public List<EstadoRankingDTO> getRankingEstados() {
        return usuarioService.buscarRankingEstados();
    }

}
