package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import java.io.Serializable;

public class BilheteVipDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(BilheteVip.class);

    private String obterIdUnico(BilheteVip bilheteVip) {
        return bilheteVip.gerarNumero();
    }

    public BilheteVip buscar(String numeroBilhete) {
        return (BilheteVip) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bv = buscar(idUnico);
        if (bv == null) {
            cadastro.incluir(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bv = buscar(idUnico);
        if (bv != null) {
            cadastro.alterar(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        BilheteVip bv = buscar(numeroBilhete);
        if (bv != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}