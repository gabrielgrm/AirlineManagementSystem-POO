package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
    private static ClienteMediator instancia;
    private ClienteDAO clienteDao = new ClienteDAO();

    private ClienteMediator() {}

    public static ClienteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }

    public Cliente buscar(String cpf) {
        return clienteDao.buscar(cpf);
    }

    public String validar(Cliente cliente) {
        if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
            return "CPF errado";
        }
        if (StringUtils.isVaziaOuNula(cliente.getNome()) || cliente.getNome().length() < 2) {
            return "nome errado";
        }
        if (cliente.getSaldoPontos() < 0) {
            return "saldo errado";
        }
        return null;
    }

    public String incluir(Cliente cliente) {
        if (validar(cliente) != null) {
            return validar(cliente);
        }
        if(!clienteDao.incluir(cliente)) {
            return "Cliente ja existente";
        } else return null;
    }

    public String alterar(Cliente cliente) {
        if (validar(cliente) != null) {
            return validar(cliente);
        }
        if(!clienteDao.alterar(cliente)) {
            return "Cliente inexistente";
        } else return null;
    }

    public String excluir(String cpf) {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            return "CPF errado";
        }
        if(!clienteDao.excluir(cpf)) {
            return "Cliente inexistente";
        } else return null;
    }
}