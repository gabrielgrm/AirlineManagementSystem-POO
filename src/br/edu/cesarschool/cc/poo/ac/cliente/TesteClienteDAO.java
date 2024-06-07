package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class TesteClienteDAO {

    public static void main(String[] args) {
        ClienteDAO cliDao = new ClienteDAO();
        Cliente c1 = new Cliente("1", "EDUARDO1", 1000.0);
        Cliente c2 = new Cliente("2", "EDUARDO2", 2000.0);
        Cliente c3 = new Cliente("3", "EDUARDO3", 3000.0);
        cliDao.incluir(c1);
        cliDao.incluir(c2);
        boolean res0 = cliDao.incluir(c3);
        System.out.println(res0);
        res0 = cliDao.incluir(c3);
        System.out.println(res0);
        c1 = cliDao.buscar("3");
        c2 = cliDao.buscar("1");
        c3 = cliDao.buscar("2");
        System.out.println(c1.getNome());
        System.out.println(c2.getNome());
        System.out.println(c3.getNome());
        c1.creditarPontos(2000.0);
        c1.setNome("CARLOS3");
        res0 = cliDao.alterar(c1);
        System.out.println(res0);
        c2 = cliDao.buscar("3");
        System.out.println(c2.getNome());
        System.out.println(c2.getSaldoPontos());
        Cliente[] clis = cliDao.buscarTodos();
        for (Cliente cliente : clis) {
            System.out.println(cliente.getNome());
        }
        c3 = new Cliente("8", "xxx", 100);
        boolean res1 = cliDao.alterar(c3);
        System.out.println(res1);
        res1 = cliDao.excluir("9");
        System.out.println(res1);
        res1 = cliDao.excluir("1");
        c3 = cliDao.buscar("1");
        System.out.println(res1);
        System.out.println(c3);

    }

}