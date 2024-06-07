package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;

public class FormaPagamentoMediator {
    private static FormaPagamentoMediator instancia;
    private static final String FORMA_PAGAMENTO_INEXISTENTE = "Forma pagamento inexistente";
    public static FormaPagamentoMediator obterInstancia() {
        if (instancia == null) {
            instancia = new FormaPagamentoMediator();
        }
        return instancia;
    }
    private FormaPagamentoMediator() {}
    private FormaPagamentoDAO dao = new FormaPagamentoDAO();
    public String validar(FormaPagamento forma) {
        if (forma == null) {
            return "Forma de pagamento nao informada";
        } else if (forma.getCodigo() < 10 || forma.getCodigo() > 99) {
            return "codigo errado";
        } else if (StringUtils.isVaziaOuNula(forma.getDescricao())) {
            return "descricao errada";
        } else if (forma.getValorMinimo() <= 0) {
            return "valor minimo errado";
        } else if (forma.getValorMaximo() <= 0 ||
                forma.getValorMaximo() < forma.getValorMinimo()) {
            return "valor maximo errado";
        }
        return null;
    }
    public String incluir(FormaPagamento forma) {
        String msg = validar(forma);
        if (msg == null) {
            if (!dao.incluir(forma)) {
                msg = "Forma pagamento ja existente";
            }
        }
        return msg;
    }
    public String alterar(FormaPagamento forma) {
        String msg = validar(forma);
        if (msg == null) {
            if (!dao.alterar(forma)) {
                msg = FORMA_PAGAMENTO_INEXISTENTE;
            }
        }
        return msg;
    }
    public String excluir(int codigo) {
        if (!dao.excluir(codigo)) {
            return FORMA_PAGAMENTO_INEXISTENTE;
        }
        return null;
    }
    public FormaPagamento buscar(int codigo) {
        return dao.buscar(codigo);
    }
}