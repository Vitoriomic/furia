package com.devmicheletto.furia.repositories;

import com.devmicheletto.furia.entities.TimeFuria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeFuriaRepository extends JpaRepository<TimeFuria, Long> {
}
