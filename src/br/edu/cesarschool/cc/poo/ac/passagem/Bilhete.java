package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

import java.sql.SQLOutput;
import java.time.LocalDateTime;

public class Bilhete extends Registro {
    private Cliente cliente;
    private Voo voo;
    private double preco;
    private double pagamentoEmPontos;
    private java.time.LocalDateTime dataHora;

    public Bilhete(Cliente cliente, Voo voo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.voo = voo;
        this.preco = preco;
        this.pagamentoEmPontos = pagamentoEmPontos;
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public double getPagamentoEmPontos() {
        return this.pagamentoEmPontos;
    }

    public double getPreco() {
        return this.preco;
    }

    public Voo getVoo() {
        return this.voo;
    }

    public double obterValorPago(){
        return this.preco - this.getPagamentoEmPontos();
    }
    public double obterValorPontuacao(){
        return this.obterValorPago() / 20;
    }

    public String gerarNumero() {
        return this.cliente.getCpf() + this.voo.getNumeroVoo() + this.dataHora.getYear() + this.dataHora.getMonthValue() + this.dataHora.getDayOfMonth();
    }
}