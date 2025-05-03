package com.devmicheletto.furia.dto;

import com.devmicheletto.furia.entities.TimeFuria;
import com.devmicheletto.furia.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String regiao;
    private String redeSocial;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private List<Long> timesFavoritosIds;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.dataNascimento = usuario.getDataNascimento();
        this.regiao = usuario.getRegiao();
        this.redeSocial = usuario.getRedeSocial();
        this.email = usuario.getEmail();
        this.timesFavoritosIds = usuario.getTimesFavoritos().stream()
                .map(TimeFuria::getId)
                .collect(Collectors.toList());
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getRedeSocial() { return redeSocial; }
    public void setRedeSocial(String redeSocial) { this.redeSocial = redeSocial; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<Long> getTimesFavoritosIds() { return timesFavoritosIds; }
    public void setTimesFavoritosIds(List<Long> timesFavoritosIds) { this.timesFavoritosIds = timesFavoritosIds; }
}
