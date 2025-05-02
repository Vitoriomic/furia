package com.devmicheletto.furia.dto;

import com.devmicheletto.furia.entities.TimeFuria;
import com.devmicheletto.furia.entities.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String regiao;
    private String redeSocial;

    private List<Long> timesFavoritosIds;

    private List<String> timesFavoritosNomes;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.dataNascimento = usuario.getDataNascimento();
        this.regiao = usuario.getRegiao();
        this.redeSocial = usuario.getRedeSocial();

        this.timesFavoritosIds = usuario.getTimesFavoritos().stream()
                .map(TimeFuria::getId)
                .collect(Collectors.toList());

        this.timesFavoritosNomes = usuario.getTimesFavoritos().stream()
                .map(TimeFuria::getNome)
                .collect(Collectors.toList());
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

    public List<Long> getTimesFavoritosIds() {
        return timesFavoritosIds;
    }

    public void setTimesFavoritosIds(List<Long> timesFavoritosIds) {
        this.timesFavoritosIds = timesFavoritosIds;
    }

    public List<String> getTimesFavoritosNomes() {
        return timesFavoritosNomes;
    }

    public void setTimesFavoritosNomes(List<String> timesFavoritosNomes) {
        this.timesFavoritosNomes = timesFavoritosNomes;
    }
}
