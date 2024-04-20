package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import java.io.Serializable;

public class BilheteDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);

    private String obterIdUnico(Bilhete bilhete) {
        return bilhete.gerarNumero();
    }

    public Bilhete buscar(String numeroBilhete) {
        return (Bilhete) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete b = buscar(idUnico);
        if (b == null) {
            cadastro.incluir(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete b = buscar(idUnico);
        if (b != null) {
            cadastro.alterar(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        Bilhete b = buscar(numeroBilhete);
        if (b != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}