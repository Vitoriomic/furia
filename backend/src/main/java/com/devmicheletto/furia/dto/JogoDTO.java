package com.devmicheletto.furia.dto;

import com.devmicheletto.furia.entities.Jogo;

import java.time.LocalDateTime;

public class JogoDTO {

    private String timeFuria;
    private String adversario;
    private LocalDateTime dataHora;
    private String campeonato;

    public JogoDTO(Jogo jogo) {
        this.timeFuria = jogo.getTimeFuria().getNome();
        this.adversario = jogo.getAdversario();
        this.dataHora = jogo.getDataHora();
        this.campeonato = jogo.getCampeonato();
    }

    public String getTimeFuria() {
        return timeFuria;
    }

    public String getAdversario() {
        return adversario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getCampeonato() {
        return campeonato;
    }
}
