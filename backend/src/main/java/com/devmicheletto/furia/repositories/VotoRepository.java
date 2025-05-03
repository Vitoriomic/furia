package com.devmicheletto.furia.repositories;

import com.devmicheletto.furia.entities.Usuario;
import com.devmicheletto.furia.entities.Voto;
import com.devmicheletto.furia.entities.Jogo;
import com.devmicheletto.furia.enums.OpcaoVoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByUsuarioAndJogo(Usuario usuario, Jogo jogo);
    long countByJogo(Jogo jogo);
    long countByJogoAndOpcao(Jogo jogo, OpcaoVoto opcao);
}
