package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

import java.time.LocalDateTime;

public class BilheteVip extends Bilhete {
    private double bonusPontuacao;

    public BilheteVip(Cliente cliente, LocalDateTime dataHora, double bonusPontuacao, double preco, Voo foundVoo, double pontuacao) {
        super();
        this.bonusPontuacao = bonusPontuacao;
    }

    public double getBonusPontuacao() {
        return bonusPontuacao;
    }

    public void setBonusPontuacao(double bonusPontuacao) {
        this.bonusPontuacao = bonusPontuacao;
    }

    public double obterValorPontuacaoVip() {
        return obterValorPontuacao() * (1+ getBonusPontuacao()/100);
    }
}
