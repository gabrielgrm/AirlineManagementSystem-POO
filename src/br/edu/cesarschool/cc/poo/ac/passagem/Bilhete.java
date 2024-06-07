package br.edu.cesarschool.cc.poo.ac.passagem;

import java.time.LocalDateTime;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class Bilhete extends Registro {
    private Cliente cliente;
    private Voo voo;
    private double preco;
    private double pagamentoEmPontos;
    private LocalDateTime dataHora;

    public Bilhete(Cliente cliente, Voo voo, double preco, double pagamentoEmPontos,
                   LocalDateTime dataHora) {
        this.cliente = cliente;
        this.voo = voo;
        this.preco = preco;
        this.pagamentoEmPontos = pagamentoEmPontos;
        this.dataHora = dataHora;
    }

    public double getPagamentoEmPontos() {
        return pagamentoEmPontos;
    }

    public void setPagamentoEmPontos(double pagamentoEmPontos) {
        this.pagamentoEmPontos = pagamentoEmPontos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Voo getVoo() {
        return voo;
    }

    public double getPreco() {
        return preco;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double obterValorPontuacao() {
        return (preco - pagamentoEmPontos)/20;
    }
    public double obterValorPago() {
        return (preco - pagamentoEmPontos);
    }
    public String gerarNumero() {
        return cliente.getCpf() + voo.getNumeroVoo() +
                dataHora.getYear() + dataHora.getMonthValue() +
                dataHora.getDayOfMonth();
    }
    public String getIdUnico() {
        return gerarNumero();
    }
}