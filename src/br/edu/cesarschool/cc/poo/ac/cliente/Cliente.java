package br.edu.cesarschool.cc.poo.ac.cliente;

public class Cliente{
    private String cpf;
    private String nome;
    private double saldoPontos;

    public Cliente(String cpf, String nome, double saldoPontos) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldoPontos = saldoPontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
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
}
