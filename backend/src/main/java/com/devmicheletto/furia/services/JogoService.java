package com.devmicheletto.furia.services;
import com.devmicheletto.furia.entities.TimeFuria;
import com.devmicheletto.furia.dto.JogoDTO;
import com.devmicheletto.furia.repositories.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<JogoDTO> listarPorTime(Long timeFuriaId) {
        return jogoRepository.findByTimeFuriaId(timeFuriaId)
                .stream()
                .map(JogoDTO::new)
                .collect(Collectors.toList());
    }


    public List<JogoDTO> listarTodos() {
        return jogoRepository.findAll()
                .stream()
                .map(JogoDTO::new)
                .collect(Collectors.toList());
    }
}
