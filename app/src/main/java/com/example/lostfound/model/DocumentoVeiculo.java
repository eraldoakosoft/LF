package com.example.lostfound.model;

public class DocumentoVeiculo {
    private String nome;
    private String CPF;
    private String placa;
    private String modelo;
    private String quemAchou;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getQuemAchou() {
        return quemAchou;
    }

    public void setQuemAchou(String quemAchou) {
        this.quemAchou = quemAchou;
    }
}
