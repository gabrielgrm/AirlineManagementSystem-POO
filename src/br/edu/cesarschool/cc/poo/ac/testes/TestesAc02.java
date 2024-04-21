package br.edu.cesarschool.cc.poo.ac.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

import java.io.Serializable;

public class TestesAc02 extends TesteGeral {
    private static final String CLIENTE_INEXISTENTE = "Cliente inexistente";
    private static final String NOME_ERRADO = "nome errado";
    private static final String CPF_ERRADO = "CPF errado";

    private static final String CPF_DV_INVALIDO = "91775395811";
    private ClienteMediator cliMed = ClienteMediator.obterInstancia();
    private CadastroObjetos cadastroCli = new CadastroObjetos(Cliente.class);

    @Test
    public void testCadCliente1() {
        excluirCadastros();
        Cliente cli = new Cliente("","MAX", 0.0);
        Assertions.assertEquals(CPF_ERRADO, cliMed.incluir(cli));
        cli = new Cliente(null,"MAX", 0.0);
        Assertions.assertEquals(CPF_ERRADO, cliMed.alterar(cli));
        cli = new Cliente(CPF_DV_INVALIDO,"MAX", 0.0);
        Assertions.assertEquals(CPF_ERRADO, cliMed.validar(cli));
        cli = new Cliente("1234567890","MAX", 0.0);
        Assertions.assertEquals(CPF_ERRADO, cliMed.validar(cli));
        cli = new Cliente("123D56h890","MAX", 0.0);
        Assertions.assertEquals(CPF_ERRADO, cliMed.incluir(cli));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_CLIENTE));
    }
    @Test
    public void testCadCliente2() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "", 0.0);
        Assertions.assertEquals(NOME_ERRADO, cliMed.validar(cli));
        cli = new Cliente(CPF_VALIDO, null, 0.0);
        Assertions.assertEquals(NOME_ERRADO, cliMed.alterar(cli));
        cli = new Cliente(CPF_VALIDO, "D", 0.0);
        Assertions.assertEquals(NOME_ERRADO, cliMed.incluir(cli));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_CLIENTE));
    }
    @Test
    public void testCadCliente3() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "DA", -1.0);
        Assertions.assertEquals("saldo errado", cliMed.validar(cli));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_CLIENTE));
    }
    @Test
    public void testCadCliente4() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "CARLOS", 200);
        cadastroCli.incluir(cli, cli.getCpf());
        Cliente cliDup = new Cliente(CPF_VALIDO, "MARIA", 300);
        Assertions.assertEquals("Cliente ja existente", cliMed.incluir(cliDup));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(1, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial((Serializable) cli, (Serializable) cliOri));
    }
    @Test
    public void testCadCliente5() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "MALCON", 112);
        cadastroCli.incluir(cli, cli.getCpf());
        Cliente cliOutro = new Cliente(OUTRO_CPF_VALIDO, "JOSIAS", 212);
        Assertions.assertEquals(null, cliMed.incluir(cliOutro));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(2, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cliOutro.getCpf());
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial((Serializable) cliOutro, (Serializable) cliOri));
    }
    @Test
    public void testCadCliente6() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "JOSA", 10);
        cadastroCli.incluir(cli, cli.getCpf());
        Cliente cliOutro = new Cliente(OUTRO_CPF_VALIDO, "BELINDA", 12);
        Assertions.assertEquals(CLIENTE_INEXISTENTE, cliMed.alterar(cliOutro));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(1, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli, cliOri));
    }
    @Test
    public void testCadCliente7() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "ZEBEDEU", 17);
        cadastroCli.incluir(cli, cli.getCpf());
        Cliente cliOutro = new Cliente(CPF_VALIDO, "SERGE", 24);
        Assertions.assertEquals(null, cliMed.alterar(cliOutro));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(1, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cliOutro, cliOri));
    }
    @Test
    public void testCadCliente8() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "KLEBER", 90);
        cadastroCli.incluir(cli, cli.getCpf());
        Assertions.assertEquals(CLIENTE_INEXISTENTE, cliMed.excluir(OUTRO_CPF_VALIDO));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(1, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli, cliOri));
    }
    @Test
    public void testCadCliente9() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "FABIANO", 66);
        cadastroCli.incluir(cli, cli.getCpf());
        Assertions.assertEquals(null, cliMed.excluir(CPF_VALIDO));
        int qtdArqs = obterQtdArquivosDir(DIR_CLIENTE);
        Assertions.assertEquals(0, qtdArqs);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertNull(cliOri);
    }
    @Test
    public void testCadCliente10() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "KLAUS", 5);
        cadastroCli.incluir(cli, cli.getCpf());
        Assertions.assertNull(cliMed.buscar(OUTRO_CPF_VALIDO));
    }
    @Test
    public void testCadCliente11() {
        excluirCadastros();
        Cliente cli = new Cliente(CPF_VALIDO, "BERG", 0);
        cadastroCli.incluir(cli, cli.getCpf());
        Cliente cliOri = cliMed.buscar(CPF_VALIDO);
        Assertions.assertNotNull(cliOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(cli, cliOri));
    }
}