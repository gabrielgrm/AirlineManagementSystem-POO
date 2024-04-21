package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

import java.time.LocalDateTime;

public class BilheteVip extends Bilhete {
    private double bonusPontuacao;
    public BilheteVip(Cliente cliente, Voo voo, double pagamentoEmPontos, double preco, LocalDateTime dataHora, double bonusPontuacao) {
        super(cliente, voo, pagamentoEmPontos, preco ,dataHora);
        this.bonusPontuacao = bonusPontuacao;
    }
    public double getBonusPontuacao() {
        return this.bonusPontuacao;
    }

    public void setBonusPontuacao(double bonusPontuacao) {
        this.bonusPontuacao = bonusPontuacao;
    }

    public double obterValorPontuacaoVip() {
        return this.obterValorPontuacao() * ((this.getBonusPontuacao()/100) + 1);
    }
}
