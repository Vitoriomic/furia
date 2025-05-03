package com.devmicheletto.furia.services;

import com.devmicheletto.furia.dto.VotoDTO;
import com.devmicheletto.furia.entities.Usuario;
import com.devmicheletto.furia.entities.Voto;
import com.devmicheletto.furia.entities.Jogo;
import com.devmicheletto.furia.enums.OpcaoVoto;
import com.devmicheletto.furia.repositories.UsuarioRepository;
import com.devmicheletto.furia.repositories.VotoRepository;
import com.devmicheletto.furia.repositories.JogoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    public VotoService(VotoRepository votoRepository, UsuarioRepository usuarioRepository, JogoRepository jogoRepository) {
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogoRepository = jogoRepository;
    }

    public void votar(Long idJogo, String emailUsuario, OpcaoVoto opcao) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Jogo jogo = jogoRepository.findById(idJogo)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        boolean jaVotou = votoRepository.existsByUsuarioAndJogo(usuario, jogo);
        if (jaVotou) {
            throw new RuntimeException("Usuário já votou nesse jogo");
        }

        Voto voto = new Voto();
        voto.setUsuario(usuario);
        voto.setJogo(jogo);
        voto.setOpcao(opcao);
        votoRepository.save(voto);
    }

    public VotoDTO calcularTermometro(Long idJogo) {
        Jogo jogo = jogoRepository.findById(idJogo)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        long total = votoRepository.countByJogo(jogo);
        if (total == 0) {
            return new VotoDTO(0, 0, 0, jogo.getAdversario());
        }

        int furia = (int) (100 * votoRepository.countByJogoAndOpcao(jogo, OpcaoVoto.FURIA) / total);
        int empate = (int) (100 * votoRepository.countByJogoAndOpcao(jogo, OpcaoVoto.EMPATE) / total);
        int adversario = (int) (100 * votoRepository.countByJogoAndOpcao(jogo, OpcaoVoto.ADVERSARIO) / total);

        return new VotoDTO(furia, empate, adversario, jogo.getAdversario());

    }
}
