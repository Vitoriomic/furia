package com.devmicheletto.furia.services;

import com.devmicheletto.furia.dto.UsuarioDTO;
import com.devmicheletto.furia.dto.UsuarioPublicoDTO;
import com.devmicheletto.furia.entities.TimeFuria;
import com.devmicheletto.furia.entities.Usuario;
import com.devmicheletto.furia.repositories.TimeFuriaRepository;
import com.devmicheletto.furia.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TimeFuriaRepository timeFuriaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, TimeFuriaRepository timeFuriaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.timeFuriaRepository = timeFuriaRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
                dto.getEmail(),
                passwordEncoder.encode(dto.getSenha()), // Criptografa a senha
                timesFavoritos
        );

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::new)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return atualizar(usuario, dto);
    }

    public UsuarioDTO atualizarPorEmail(String email, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));

        return atualizar(usuario, dto);
    }

    private UsuarioDTO atualizar(Usuario usuario, UsuarioDTO dto) {
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setRedeSocial(dto.getRedeSocial());
        usuario.setRegiao(dto.getRegiao());

        if (dto.getTimesFavoritosIds() != null) {
            List<TimeFuria> times = dto.getTimesFavoritosIds().stream()
                    .map(id -> timeFuriaRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Time com ID " + id + " não encontrado.")))
                    .collect(Collectors.toList());
            usuario.setTimesFavoritos(times);
        }

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public List<UsuarioPublicoDTO> listarPublicamente() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioPublicoDTO::new)
                .collect(Collectors.toList());
    }
}
