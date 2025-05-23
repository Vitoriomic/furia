package com.devmicheletto.furia.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "time_furia")
public class TimeFuria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public TimeFuria() {
    }

    public TimeFuria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TimeFuria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
