package br.edu.cesarschool.cc.poo.telas.modelo;

public class ProdutoMediator {
    public String incluir(Produto prod) {
        if (prod == null || prod.getNome() == null ||
                prod.getNome().trim().equals("")) {
            return "Produto inválido";
        } else {
            return null;
        }
    }
    public String alterar(Produto prod) {
        if (prod == null || prod.getNome() == null ||
                prod.getNome().trim().equals("")) {
            return "Produto inválido";
        } else {
            return null;
        }
    }
    public String excluir(long codigo) {
        if (codigo <= 0) {
            return "Produto inexistente";
        } else {
            return null;
        }
    }
    public Produto buscar(long codigo) {
        if (codigo <=0 || codigo > 1000) {
            return null;
        } else {
            return new Produto(codigo,"LEITE");
        }
    }


}