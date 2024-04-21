package br.edu.cesarschool.cc.poo.telas.modelo;

public class Produto {
    private long codigo;
    private String nome;
    public Produto(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getCodigo() {
        return codigo;
    }

}