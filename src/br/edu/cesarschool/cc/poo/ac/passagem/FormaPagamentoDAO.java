package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class FormaPagamentoDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(FormaPagamento.class);
    public FormaPagamento buscar(int codigo) {
        return (FormaPagamento)cadastro.buscar(codigo+"");
    }
    private String obterIdUnicoFormaPagamento(FormaPagamento formaPagamento) {
        return formaPagamento.getCodigo() + "";
    }
    public boolean incluir(FormaPagamento formaPagamento) {
        FormaPagamento cli = buscar(formaPagamento.getCodigo());
        if (cli == null) {
            cadastro.incluir(formaPagamento, obterIdUnicoFormaPagamento(formaPagamento));
            return true;
        }
        return false;
    }
    public boolean alterar(FormaPagamento formaPagamento) {
        FormaPagamento cli = buscar(formaPagamento.getCodigo());
        if (cli != null) {
            cadastro.alterar(formaPagamento, obterIdUnicoFormaPagamento(formaPagamento));
            return true;
        }
        return false;
    }
    public boolean excluir(int codigo) {
        FormaPagamento cli = buscar(codigo);
        if (cli != null) {
            cadastro.excluir(codigo + "");
            return true;
        }
        return false;
    }
    public FormaPagamento[] buscarTodos() {
        Serializable[] res = cadastro.buscarTodos();
        if (res == null) {
            return null;
        } else {
            FormaPagamento[] voos = new FormaPagamento[res.length];
            int i = 0;
            for (Serializable reg : res) {
                voos[i] = (FormaPagamento)reg;
                i++;
            }
            return voos;
        }
    }
}