package com.devmicheletto.furia.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_furia_id")
    private TimeFuria timeFuria;

    private String adversario;

    private LocalDateTime dataHora;

    private String campeonato;

    public Jogo() {}

    public Jogo(TimeFuria timeFuria, String adversario, LocalDateTime dataHora, String campeonato) {
        this.timeFuria = timeFuria;
        this.adversario = adversario;
        this.dataHora = dataHora;
        this.campeonato = campeonato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeFuria getTimeFuria() {
        return timeFuria;
    }

    public void setTimeFuria(TimeFuria timeFuria) {
        this.timeFuria = timeFuria;
    }

    public String getAdversario() {
        return adversario;
    }

    public void setAdversario(String adversario) {
        this.adversario = adversario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(String campeonato) {
        this.campeonato = campeonato;
    }
}
