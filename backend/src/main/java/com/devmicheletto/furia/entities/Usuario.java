package com.devmicheletto.furia.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String regiao;

    private String redeSocial;

    @ManyToMany
    @JoinTable(
            name = "usuario_times_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "times_furia_id")
    )
    private List<TimeFuria> timesFavoritos;

    public Usuario() {
    }

    public Usuario(String nome, LocalDate dataNascimento, String regiao, String redeSocial, List<TimeFuria> timesFavoritos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.regiao = regiao;
        this.redeSocial = redeSocial;
        this.timesFavoritos = timesFavoritos;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public List<TimeFuria> getTimesFavoritos() {
        return timesFavoritos;
    }

    public void setTimesFavoritos(List<TimeFuria> timesFavoritos) {
        this.timesFavoritos = timesFavoritos;
    }
}
