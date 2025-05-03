package com.devmicheletto.furia.entities;

import com.devmicheletto.furia.enums.OpcaoVoto;
import jakarta.persistence.*;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Jogo jogo;

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public OpcaoVoto getOpcao() { return opcao; }
    public void setOpcao(OpcaoVoto opcao) { this.opcao = opcao; }
}
