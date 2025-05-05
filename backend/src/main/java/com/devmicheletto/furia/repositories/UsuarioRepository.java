package com.devmicheletto.furia.repositories;

import com.devmicheletto.furia.dto.EstadoRankingDTO;
import com.devmicheletto.furia.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT new com.devmicheletto.furia.dto.EstadoRankingDTO(u.regiao, COUNT(u)) " +
            "FROM Usuario u GROUP BY u.regiao ORDER BY COUNT(u) DESC")
    List<EstadoRankingDTO> buscarRankingEstados();

}
