package com.devmicheletto.furia.dto;

import com.devmicheletto.furia.entities.Usuario;
import com.devmicheletto.furia.entities.TimeFuria;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPublicoDTO {

    private String nome;
    private String regiao;
    private String redeSocial;
    private List<String> timesFavoritos;

    public UsuarioPublicoDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.regiao = usuario.getRegiao();
        this.redeSocial = usuario.getRedeSocial();
        this.timesFavoritos = usuario.getTimesFavoritos()
                .stream()
                .map(TimeFuria::getNome)
                .collect(Collectors.toList());
    }

    // Getters
    public String getNome() { return nome; }
    public String getRegiao() { return regiao; }
    public String getRedeSocial() { return redeSocial; }
    public List<String> getTimesFavoritos() { return timesFavoritos; }
}
