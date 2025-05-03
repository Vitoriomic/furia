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
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    // Busca usuário por ID
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado."));
        return new UsuarioDTO(usuario);
    }

    // Atualiza os dados do usuário
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado."));

        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setRegiao(dto.getRegiao());
        usuario.setRedeSocial(dto.getRedeSocial());

        // Atualiza os times favoritos
        List<TimeFuria> novosTimes = dto.getTimesFavoritosIds().stream()
                .map(tid -> timeFuriaRepository.findById(tid)
                        .orElseThrow(() -> new RuntimeException("Time com ID " + tid + " não encontrado.")))
                .collect(Collectors.toList());
        usuario.setTimesFavoritos(novosTimes);

        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }
}
