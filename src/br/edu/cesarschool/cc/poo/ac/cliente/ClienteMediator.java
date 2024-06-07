package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
    private static final String CLIENTE_INEXISTENTE = "Cliente inexistente";
    private static final String CPF_ERRADO = "CPF errado";
    private static final int TAM_MIN_NOME = 2;
    private static final String NOME_ERRADO = "nome errado";
    private static ClienteMediator instancia;
    private ClienteDAO clienteDao = new ClienteDAO();
    public static ClienteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }
    private ClienteMediator() {

    }
    public String validar(Cliente cliente) {
        if (cliente == null) {
            return "Cliente nao informado";
        } else if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
            return CPF_ERRADO;
        } else if (StringUtils.isVaziaOuNula(cliente.getNome())) {
            return NOME_ERRADO;
        } else if (cliente.getNome().length() < TAM_MIN_NOME) {
            return NOME_ERRADO;
        } else if (cliente.getSaldoPontos() < 0) {
            return "saldo errado";
        }
        return null;
    }
    public String incluir(Cliente cliente) {
        String msg = validar(cliente);
        if (msg == null) {
            if (!clienteDao.incluir(cliente)) {
                msg = "Cliente ja existente";
            }
        }
        return msg;
    }
    public String alterar(Cliente cliente) {
        String msg = validar(cliente);
        if (msg == null) {
            if (!clienteDao.alterar(cliente)) {
                msg = CLIENTE_INEXISTENTE;
            }
        }
        return msg;
    }
    public Cliente buscar(String cpf) {
        return clienteDao.buscar(cpf);
    }
    public String excluir(String cpf) {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            return CPF_ERRADO;
        } else if (!clienteDao.excluir(cpf)) {
            return CLIENTE_INEXISTENTE;
        }
        return null;
    }
}