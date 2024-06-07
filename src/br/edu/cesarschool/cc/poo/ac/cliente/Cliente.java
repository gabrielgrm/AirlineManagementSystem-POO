package br.edu.cesarschool.cc.poo.ac.cliente;

import java.io.Serializable;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class Cliente extends Registro {
    private String cpf;
    private String nome;
    private double saldoPontos;
    public Cliente(String cpf, String nome, double saldoPontos) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldoPontos = saldoPontos;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public double getSaldoPontos() {
        return saldoPontos;
    }
    public void creditarPontos(double valor) {
        saldoPontos += valor;
    }
    public void debitarPontos(double valor) {
        saldoPontos -= valor;
    }
    public String getIdUnico() {
        return getCpf();
    }
}