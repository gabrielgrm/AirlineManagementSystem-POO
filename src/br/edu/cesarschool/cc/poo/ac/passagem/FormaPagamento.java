package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class FormaPagamento extends Registro {
    private int codigo;
    private String descricao;
    private double valorMinimo;
    private double valorMaximo;
    public FormaPagamento(int codigo, String descricao, double valorMinimo, double valorMaximo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValorMinimo() {
        return valorMinimo;
    }
    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
    public double getValorMaximo() {
        return valorMaximo;
    }
    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }
}