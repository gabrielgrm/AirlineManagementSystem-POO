package br.edu.cesarschool.cc.poo.ac.testes;

import br.edu.cesarschool.cc.poo.ac.passagem.FormaPagamento;
import br.edu.cesarschool.cc.poo.ac.passagem.FormaPagamentoMediator;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestesAc05 extends TesteGeral {
    private static final String FORMA_PAGAMENTO_INEXISTENTE = "Forma pagamento inexistente";
    private static final String VALOR_MAXIMO_ERRADO = "valor maximo errado";
    private static final String VALOR_MINIMO_ERRADO = "valor minimo errado";
    private static final String DESCRICAO_ERRADA = "descricao errada";
    private static final String CODIGO_ERRADO = "codigo errado";

    private FormaPagamentoMediator formaMed = FormaPagamentoMediator.obterInstancia();
    private CadastroObjetos cadastroForma = new CadastroObjetos(FormaPagamento.class);

    @Test
    public void test1() {
        excluirCadastros();
        FormaPagamento forma = new FormaPagamento(1, "Bla bla bla", 10, 10000);
        Assertions.assertEquals(CODIGO_ERRADO, formaMed.incluir(forma));
        forma = new FormaPagamento(109, "De de de", 11, 10001);
        Assertions.assertEquals(CODIGO_ERRADO, formaMed.incluir(forma));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_FORMA));
    }
    @Test
    public void test2() {
        excluirCadastros();
        FormaPagamento forma = new FormaPagamento(12, "", 12, 10002);
        Assertions.assertEquals(DESCRICAO_ERRADA, formaMed.incluir(forma));
        forma = new FormaPagamento(88, null, 15, 201);
        Assertions.assertEquals(DESCRICAO_ERRADA, formaMed.incluir(forma));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_FORMA));
    }
    @Test
    public void test3() {
        excluirCadastros();
        FormaPagamento forma = new FormaPagamento(15, "gi gi gi", 0, 10005);
        Assertions.assertEquals(VALOR_MINIMO_ERRADO, formaMed.incluir(forma));
        forma = new FormaPagamento(89, "da da da", -20, 1992);
        Assertions.assertEquals(VALOR_MINIMO_ERRADO, formaMed.incluir(forma));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_FORMA));
    }
    @Test
    public void test4() {
        excluirCadastros();
        FormaPagamento forma = new FormaPagamento(15, "ce ce ce", 20, 0);
        Assertions.assertEquals(VALOR_MAXIMO_ERRADO, formaMed.incluir(forma));
        forma = new FormaPagamento(98, "say say say", 1, -9);
        Assertions.assertEquals(VALOR_MAXIMO_ERRADO, formaMed.incluir(forma));
        forma = new FormaPagamento(98, "say say say", 2022, 699);
        Assertions.assertEquals(VALOR_MAXIMO_ERRADO, formaMed.incluir(forma));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_FORMA));
    }
    @Test
    public void test5() {
        excluirCadastros();
        int codigo = 76;
        FormaPagamento forma = new FormaPagamento(codigo, "du du du", 10, 80000);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        FormaPagamento formaDup = new FormaPagamento(codigo, "be de be", 60, 409100);
        Assertions.assertEquals("Forma pagamento ja existente", formaMed.incluir(formaDup));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(1, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma, formaOri));
    }
    @Test
    public void test6() {
        excluirCadastros();
        int codigo = 72;
        FormaPagamento forma = new FormaPagamento(66, "Xe xe", 2000, 20000);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        FormaPagamento forma1 = new FormaPagamento(codigo, "di di di", 1000, 75000);
        Assertions.assertEquals(null, formaMed.incluir(forma1));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(2, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma1, formaOri));
    }
    @Test
    public void test7() {
        excluirCadastros();
        int codigo = 76;
        FormaPagamento forma = new FormaPagamento(codigo, "ki ki ki", 0.01, 1000000000);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        FormaPagamento formaAlt = new FormaPagamento(21, "la la la", 1, 2000000);
        Assertions.assertEquals(FORMA_PAGAMENTO_INEXISTENTE, formaMed.alterar(formaAlt));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(1, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma, formaOri));
    }
    @Test
    public void test8() {
        excluirCadastros();
        int codigo = 72;
        FormaPagamento forma = new FormaPagamento(codigo, "Ser ser", 2300, 23000);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        FormaPagamento forma1 = new FormaPagamento(codigo, "Padim padim", 1300, 85000);
        Assertions.assertEquals(null, formaMed.alterar(forma1));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(1, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma1, formaOri));
    }
    @Test
    public void test9() {
        excluirCadastros();
        FormaPagamento forma = new FormaPagamento(43, "ha ha ha", 2905, 29801);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        Assertions.assertNull(formaMed.buscar(42));
    }
    @Test
    public void test10() {
        excluirCadastros();
        int codigo = 45;
        FormaPagamento forma = new FormaPagamento(codigo, "ha ha ha", 2905, 29801);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        FormaPagamento formaOri = formaMed.buscar(codigo);
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma, formaOri));
    }
    @Test
    public void test11() {
        excluirCadastros();
        int codigo = 51;
        FormaPagamento forma = new FormaPagamento(codigo, "kkkkkkkkkk", 4433, 444455);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        Assertions.assertEquals(FORMA_PAGAMENTO_INEXISTENTE, formaMed.excluir(56));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(1, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNotNull(formaOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(forma, formaOri));
    }
    @Test
    public void test12() {
        excluirCadastros();
        int codigo = 65;
        FormaPagamento forma = new FormaPagamento(codigo, "LOL!", 332, 121221);
        cadastroForma.incluir(forma, forma.getCodigo()+"");
        Assertions.assertEquals(null, formaMed.excluir(codigo));
        int qtdArqs = obterQtdArquivosDir(DIR_FORMA);
        Assertions.assertEquals(0, qtdArqs);
        FormaPagamento formaOri = (FormaPagamento)cadastroForma.buscar(codigo+"");
        Assertions.assertNull(formaOri);
    }
}