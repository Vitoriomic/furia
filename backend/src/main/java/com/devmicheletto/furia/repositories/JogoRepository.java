package com.devmicheletto.furia.repositories;

import com.devmicheletto.furia.entities.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.devmicheletto.furia.entities.TimeFuria;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByTimeFuriaId(Long timeFuriaId);
}
