package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

class ResultadoAuxiliar {
    private Voo voo;
    private Cliente cliente;
    private ResultadoGeracaoBilhete res;
    private double valorNecessarioPontos;
    ResultadoAuxiliar(Voo voo, Cliente cliente, ResultadoGeracaoBilhete res,
                      double valorNecessarioPontos) {
        this.voo = voo;
        this.cliente = cliente;
        this.res = res;
        this.valorNecessarioPontos = valorNecessarioPontos;
    }
    Voo getVoo() {
        return voo;
    }
    Cliente getCliente() {
        return cliente;
    }
    ResultadoGeracaoBilhete getRes() {
        return res;
    }
    double getValorNecessarioPontos() {
        return valorNecessarioPontos;
    }

}