package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;
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
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getPagamentoEmPontos() {
        return pagamentoEmPontos;
    }

    public double getPreco() {
        return preco;
    }

    public Voo getVoo() {
        return voo;
    }

    public double obterValorPago(){
        return preco - getPagamentoEmPontos();
    }
    public double obterValorPontuacao(){
        return obterValorPago() / 20;
    }

    public String gerarNumero() {
        return cliente.getCpf() + voo.getNumeroVoo() + dataHora.getYear() + dataHora.getMonthValue() + dataHora.getDayOfMonth();
    }
}