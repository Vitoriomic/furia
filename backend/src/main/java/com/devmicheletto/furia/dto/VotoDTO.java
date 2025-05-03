package com.devmicheletto.furia.dto;

public class VotoDTO {

    private int furia;
    private int empate;
    private int adversario;
    private String nomeAdversario;

    public VotoDTO(int furia, int empate, int adversario, String nomeAdversario) {
        this.furia = furia;
        this.empate = empate;
        this.adversario = adversario;
        this.nomeAdversario = nomeAdversario;
    }

    public int getFuria() {
        return furia;
    }

    public void setFuria(int furia) {
        this.furia = furia;
    }

    public int getEmpate() {
        return empate;
    }

    public void setEmpate(int empate) {
        this.empate = empate;
    }

    public int getAdversario() {
        return adversario;
    }

    public void setAdversario(int adversario) {
        this.adversario = adversario;
    }

    public String getNomeAdversario() {
        return nomeAdversario;
    }

    public void setNomeAdversario(String nomeAdversario) {
        this.nomeAdversario = nomeAdversario;
    }
}
