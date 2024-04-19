package br.edu.cesarschool.cc.poo.ac.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Registro implements Serializable {
    private LocalDateTime dhInclusao;
    private LocalDateTime dhUltimaAtualizacao;
    public Registro() {
        dhInclusao = LocalDateTime.now();
    }
    public LocalDateTime getDhUltimaAtualizacao() {
        return dhUltimaAtualizacao;
    }
    public void setDhUltimaAtualizacao(LocalDateTime dhUltimaAtualizacao) {
        this.dhUltimaAtualizacao = dhUltimaAtualizacao;
    }
    public LocalDateTime getDhInclusao() {
        return dhInclusao;
    }
}