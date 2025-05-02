package com.devmicheletto.furia.services;

import com.devmicheletto.furia.dto.UsuarioDTO;
import com.devmicheletto.furia.entities.TimeFuria;
import com.devmicheletto.furia.entities.Usuario;
import com.devmicheletto.furia.repositories.TimeFuriaRepository;
import com.devmicheletto.furia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TimeFuriaRepository timeFuriaRepository;

    // Cria um novo usuário com lista de IDs dos times favoritos
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        List<TimeFuria> timesFavoritos = dto.getTimesFavoritosIds().stream()
                .map(id -> timeFuriaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Time com ID " + id + " não encontrado.")))
                .collect(Collectors.toList());

        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getDataNascimento(),
                dto.getRegiao(),
                dto.getRedeSocial(),
                timesFavoritos
        );

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    // Retorna todos os usuários cadastrados
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }
}

