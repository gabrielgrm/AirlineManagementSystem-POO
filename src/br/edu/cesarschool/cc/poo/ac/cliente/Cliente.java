package br.edu.cesarschool.cc.poo.ac.cliente;
import java.io.Serializable;
public class Cliente implements Serializable {
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
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public double getSaldoPontos() {
        return this.saldoPontos;
    }
    public void creditarPontos(double valor) {
        this.saldoPontos += valor;
    }
    public void debitarPontos(double valor) {
        this.saldoPontos -= valor;
    }
}
