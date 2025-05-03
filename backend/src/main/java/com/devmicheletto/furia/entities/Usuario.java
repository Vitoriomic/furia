package com.devmicheletto.furia.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String regiao;

    private String redeSocial;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuario_times_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "times_furia_id")
    )
    private List<TimeFuria> timesFavoritos;

    public Usuario() {
    }

    public Usuario(String nome, LocalDate dataNascimento, String regiao, String redeSocial,
                   String email, String senha, List<TimeFuria> timesFavoritos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.regiao = regiao;
        this.redeSocial = redeSocial;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
