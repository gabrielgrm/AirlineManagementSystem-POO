package br.edu.cesarschool.cc.poo.ac.testes;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteVip;
import br.edu.cesarschool.cc.poo.ac.passagem.ResultadoGeracaoBilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class TestesAc04 extends TesteGeral {
    private static final String BILHETE_JA_EXISTENTE = "Bilhete ja existente";
    private static final String PONTOS_INSUFICIENTES = "Pontos insuficientes";
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente nao encontrado";
    private static final String VOO_INEXISTENTE = "Voo nao encontrado";
    private static final String DATA_HORA_INVALIDA = "data hora invalida";
    private static final String PRECO_MENOR_QUE_PAGAMENTO_EM_PONTOS = "Preco menor que pagamento em pontos";
    private static final String PAGAMENTO_PONTOS_ERRADO = "Pagamento pontos errado";
    private static final String PRECO_ERRADO = "Preco errado";
    private static final String NUMERO_VOO_ERRADO = "Numero voo errado";
    private static final String CIA_AEREA_ERRADA = "CIA aerea errada";
    private static final String CPF_ERRADO = "CPF errado";
    private static final Cliente CLI1 = new Cliente(OUTRO_CPF_VALIDO, "MARIO", 100);
    private static final Voo VOO1 = new Voo("REC", "GRU", "JJ", 1231);
    private BilheteMediator biMed = BilheteMediator.obterInstancia();
    private CadastroObjetos cadastroBi = new CadastroObjetos(Bilhete.class);
    private CadastroObjetos cadastroBiVip = new CadastroObjetos(BilheteVip.class);
    private CadastroObjetos cadastroVoo = new CadastroObjetos(Voo.class);
    private CadastroObjetos cadastroCli = new CadastroObjetos(Cliente.class);

    @Test
    public void testBilhete1() {
        excluirCadastros();
        LocalDateTime dh = LocalDateTime.parse("2200-01-01T00:00:00");
        Bilhete bi = new Bilhete(CLI1, VOO1, 1500, 500, dh);
        cadastroBi.incluir(bi, bi.gerarNumero());
        Assertions.assertNull(biMed.buscar("690251835141231210011"));
    }
    @Test
    public void testBilhete2() {
        excluirCadastros();
        LocalDateTime dh = LocalDateTime.parse("2200-10-10T00:00:00");
        BilheteVip bi = new BilheteVip(CLI1, VOO1, 2222, 111, dh, 10);
        cadastroBiVip.incluir(bi, bi.gerarNumero());
        Assertions.assertNull(biMed.buscarVip("69025183514123122001110"));
    }
    @Test
    public void testBilhete3() {
        excluirCadastros();
        LocalDateTime dh = LocalDateTime.parse("2222-11-22T11:30:00");
        Bilhete bi = new Bilhete(CLI1, VOO1, 1211, 112, dh);
        cadastroBi.incluir(bi, bi.gerarNumero());
        Bilhete biOri = biMed.buscar("69025183514123122221122");
        Assertions.assertNotNull(biOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(bi, biOri));
    }
    @Test
    public void testBilhete4() {
        excluirCadastros();
        LocalDateTime dh = LocalDateTime.parse("2333-11-11T10:20:00");
        BilheteVip bi = new BilheteVip(CLI1, VOO1, 4333, 212, dh, 15);
        cadastroBiVip.incluir(bi, bi.gerarNumero());
        BilheteVip biOri = biMed.buscarVip("69025183514123123331111");
        Assertions.assertNotNull(biOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(bi, biOri));
    }
    private void assertCpfErrado(ResultadoGeracaoBilhete res) {
        assertResErro(res, CPF_ERRADO);
    }
    @Test
    public void testBilhete5() {
        excluirCadastros();
        String cpf = null;
        String ciaAerea = "JJ";
        int numeroVoo = 1212;
        double preco = 1000.00;
        double pagamentoEmPontos = 400.0;
        double bonus = 10;
        LocalDateTime dataHora = LocalDateTime.parse("2201-12-13T15:11:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCpfErrado(res);
        assertCpfErrado(res1);
        cpf = "";
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCpfErrado(res);
        assertCpfErrado(res1);
        cpf = "12345678901";
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCpfErrado(res);
        assertCpfErrado(res1);
        cpf = "123456789";
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCpfErrado(res);
        assertCpfErrado(res1);
        cpf = "1A34B6789C";
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCpfErrado(res);
        assertCpfErrado(res1);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));

    }
    private void assertCiaAereaErrada(ResultadoGeracaoBilhete res) {
        assertResErro(res, CIA_AEREA_ERRADA);
    }
    private void assertResErro(ResultadoGeracaoBilhete res, String msg) {
        Assertions.assertNotNull(res);
        Assertions.assertNull(res.getBilhete());
        Assertions.assertNull(res.getBilheteVip());
        Assertions.assertNotNull(res.getMensagemErro());
        Assertions.assertEquals(msg, res.getMensagemErro());
    }
    @Test
    public void testBilhete6() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "";
        int numeroVoo = 1216;
        double preco = 1001.00;
        double pagamentoEmPontos = 600.0;
        double bonus = 12;
        LocalDateTime dataHora = LocalDateTime.parse("2202-12-27T15:11:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCiaAereaErrada(res);
        assertCiaAereaErrada(res1);
        ciaAerea = null;
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCiaAereaErrada(res);
        assertCiaAereaErrada(res1);
        ciaAerea = "JJK";
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertCiaAereaErrada(res);
        assertCiaAereaErrada(res1);
        ciaAerea = "YW";
        numeroVoo = 11;
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, NUMERO_VOO_ERRADO);
        assertResErro(res1, NUMERO_VOO_ERRADO);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete7() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "HH";
        int numeroVoo = 1111;
        double preco = -100;
        double pagamentoEmPontos = 600.0;
        double bonus = 14;
        LocalDateTime dataHora = LocalDateTime.parse("2280-02-28T15:11:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, PRECO_ERRADO);
        assertResErro(res1, PRECO_ERRADO);
        preco = 0.0;
        res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, PRECO_ERRADO);
        assertResErro(res1, PRECO_ERRADO);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete8() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "HH";
        int numeroVoo = 1111;
        double preco = 204;
        double pagamentoEmPontos = -101.0;
        double bonus = 22;
        LocalDateTime dataHora = LocalDateTime.parse("2222-09-30T15:11:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, PAGAMENTO_PONTOS_ERRADO);
        assertResErro(res1, PAGAMENTO_PONTOS_ERRADO);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete9() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "HH";
        int numeroVoo = 1111;
        double preco = 100;
        double pagamentoEmPontos = 101;
        double bonus = 5;
        LocalDateTime dataHora = LocalDateTime.parse("2240-11-29T15:22:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, PRECO_MENOR_QUE_PAGAMENTO_EM_PONTOS);
        assertResErro(res1, PRECO_MENOR_QUE_PAGAMENTO_EM_PONTOS);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete10() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "GV";
        int numeroVoo = 9078;
        double preco = 2344;
        double pagamentoEmPontos = 34;
        double bonus = 7;
        LocalDateTime dataHora = LocalDateTime.parse("2001-01-01T23:22:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, DATA_HORA_INVALIDA);
        assertResErro(res1, DATA_HORA_INVALIDA);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete11() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "GV";
        int numeroVoo = 9078;
        double preco = 2344;
        double pagamentoEmPontos = 34;
        double bonus = 7;
        LocalDateTime dataHora = LocalDateTime.parse("2221-12-12T23:22:00");
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, VOO_INEXISTENTE);
        assertResErro(res1, VOO_INEXISTENTE);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete12() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "GV";
        int numeroVoo = 2000;
        double preco = 2344;
        double pagamentoEmPontos = 34;
        double bonus = 7;
        LocalDateTime dataHora = LocalDateTime.parse("2229-12-12T23:22:00");
        Voo voo = new Voo("GIG", "CGH", ciaAerea, numeroVoo);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, CLIENTE_NAO_ENCONTRADO);
        assertResErro(res1, CLIENTE_NAO_ENCONTRADO);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete13() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "XX";
        int numeroVoo = 2001;
        double preco = 7800;
        double pagamentoEmPontos = 3500;
        double bonus = 25;
        LocalDateTime dataHora = LocalDateTime.parse("2234-11-11T23:22:00");
        Voo voo = new Voo("FOR", "BEL", ciaAerea, numeroVoo);
        Cliente cli = new Cliente(cpf, "CLAUDIO", 67000);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        cadastroCli.incluir(cli, cli.getCpf());
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora, bonus);
        assertResErro(res, PONTOS_INSUFICIENTES);
        assertResErro(res1, PONTOS_INSUFICIENTES);
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    @Test
    public void testBilhete14() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "TG";
        int numeroVoo = 9009;
        double preco = 5250;
        double pagamentoEmPontos = 250;
        double valorPago = 5000;
        double valorPontuacao1 = 250;
        double valorPontuacao2 = 275;
        double saldoCliente1 = 10250.0;
        double saldoCliente2 = 5525.0;
        double bonus = 10;
        String aeOri = "SSA";
        String aeDes = "BSB";
        String nome = "MARIAH";
        double saldo = 15000;
        LocalDateTime dataHora = LocalDateTime.parse("2231-08-11T22:10:00");
        LocalDateTime dataHora1 = LocalDateTime.parse("2232-08-11T22:10:00");
        Voo voo = new Voo(aeOri, aeDes, ciaAerea, numeroVoo);
        Cliente cli = new Cliente(cpf, nome, saldo);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        cadastroCli.incluir(cli, cli.getCpf());
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora1, bonus);
        assertSucesso(res);
        assertSucessoVip(res1);
        assertDadosBilhete(res.getBilhete(), preco, pagamentoEmPontos, valorPago, valorPontuacao1, dataHora, voo,
                cli,"9177539583990092231811", saldoCliente1);
        assertDadosBilheteVip(res1.getBilheteVip(), preco, pagamentoEmPontos, valorPago, valorPontuacao2, dataHora1, voo,
                cli,"9177539583990092232811", saldoCliente2);
        Cliente cliOri = (Cliente)cadastroCli.buscar(cli.getCpf());
        Assertions.assertEquals(saldoCliente2, cliOri.getSaldoPontos());
        Assertions.assertEquals(cli.getNome(), cliOri.getNome());
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_VOO));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }
    private void assertSucesso(ResultadoGeracaoBilhete res) {
        Assertions.assertNull(res.getMensagemErro());
        Assertions.assertNotNull(res);
        Assertions.assertNotNull(res.getBilhete());
        Assertions.assertNull(res.getBilheteVip());

    }
    private void assertSucessoVip(ResultadoGeracaoBilhete res) {
        Assertions.assertNull(res.getMensagemErro());
        Assertions.assertNotNull(res);
        Assertions.assertNull(res.getBilhete());
        Assertions.assertNotNull(res.getBilheteVip());
    }
    private void assertDadosBilhete(Bilhete bilhete, double preco, double pagamentoEmPontos,
                                    double valorPago, double valorPontuacao, LocalDateTime dataHora, Voo voo, Cliente cli,
                                    String numeroBilhete, double saldoCliente) {
        Assertions.assertNotNull(bilhete.getVoo());
        Assertions.assertNotNull(bilhete.getCliente());
        Assertions.assertEquals(preco, bilhete.getPreco());
        Assertions.assertEquals(pagamentoEmPontos, bilhete.getPagamentoEmPontos());
        Assertions.assertEquals(valorPago, bilhete.obterValorPago());
        Assertions.assertEquals(valorPontuacao, bilhete.obterValorPontuacao());
        Assertions.assertEquals(dataHora, bilhete.getDataHora());
        Assertions.assertEquals(voo.getNumeroVoo(), bilhete.getVoo().getNumeroVoo());
        Assertions.assertEquals(voo.getCompanhiaAerea(), bilhete.getVoo().getCompanhiaAerea());
        Assertions.assertEquals(voo.getAeroportoOrigem(), bilhete.getVoo().getAeroportoOrigem());
        Assertions.assertEquals(voo.getAeroportoDestino(), bilhete.getVoo().getAeroportoDestino());
        Assertions.assertEquals(saldoCliente, bilhete.getCliente().getSaldoPontos());
        Assertions.assertEquals(cli.getCpf(), bilhete.getCliente().getCpf());
        Assertions.assertEquals(cli.getNome(), bilhete.getCliente().getNome());
        Assertions.assertEquals(numeroBilhete, bilhete.gerarNumero());
        Assertions.assertNotNull(bilhete.getDhInclusao());
    }
    private void assertDadosBilheteVip(BilheteVip bilhete, double preco, double pagamentoEmPontos,
                                       double valorPago, double valorPontuacao, LocalDateTime dataHora, Voo voo, Cliente cli,
                                       String numeroBilhete, double saldoCliente) {
        Assertions.assertNotNull(bilhete.getVoo());
        Assertions.assertNotNull(bilhete.getCliente());
        Assertions.assertEquals(preco, bilhete.getPreco());
        Assertions.assertEquals(pagamentoEmPontos, bilhete.getPagamentoEmPontos());
        Assertions.assertEquals(valorPago, bilhete.obterValorPago());
        Assertions.assertEquals(valorPontuacao, bilhete.obterValorPontuacaoVip());
        Assertions.assertEquals(dataHora, bilhete.getDataHora());
        Assertions.assertEquals(voo.getNumeroVoo(), bilhete.getVoo().getNumeroVoo());
        Assertions.assertEquals(voo.getCompanhiaAerea(), bilhete.getVoo().getCompanhiaAerea());
        Assertions.assertEquals(voo.getAeroportoOrigem(), bilhete.getVoo().getAeroportoOrigem());
        Assertions.assertEquals(voo.getAeroportoDestino(), bilhete.getVoo().getAeroportoDestino());
        Assertions.assertEquals(saldoCliente, bilhete.getCliente().getSaldoPontos());
        Assertions.assertEquals(cli.getCpf(), bilhete.getCliente().getCpf());
        Assertions.assertEquals(cli.getNome(), bilhete.getCliente().getNome());
        Assertions.assertEquals(numeroBilhete, bilhete.gerarNumero());
        Assertions.assertNotNull(bilhete.getDhInclusao());
    }
    @Test
    public void testBilhete15() {
        excluirCadastros();
        String cpf = CPF_VALIDO;
        String ciaAerea = "MN";
        int numeroVoo = 9001;
        double preco = 4000;
        double pagamentoEmPontos = 0;
        double bonus = 10;
        String aeOri = "MAO";
        String aeDes = "RBR";
        String nome = "JOSE";
        double saldo = 20;
        LocalDateTime dataHora = LocalDateTime.parse("2235-08-11T22:10:00");
        LocalDateTime dataHora1 = LocalDateTime.parse("2236-08-11T22:10:00");
        Voo voo = new Voo(aeOri, aeDes, ciaAerea, numeroVoo);
        Cliente cli = new Cliente(cpf, nome, saldo);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        cadastroCli.incluir(cli, cli.getCpf());
        Bilhete bi = new Bilhete(cli, voo, preco, pagamentoEmPontos, dataHora);
        Bilhete vip = new BilheteVip(cli, voo, preco, pagamentoEmPontos, dataHora1, bonus);
        cadastroBi.incluir(bi, bi.gerarNumero());
        cadastroBiVip.incluir(vip, vip.gerarNumero());
        ResultadoGeracaoBilhete res = biMed.gerarBilhete(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora);
        ResultadoGeracaoBilhete res1 = biMed.gerarBilheteVip(cpf, ciaAerea, numeroVoo,
                preco, pagamentoEmPontos, dataHora1, bonus);
        assertResErro(res, BILHETE_JA_EXISTENTE);
        assertResErro(res1, BILHETE_JA_EXISTENTE);
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_CLIENTE));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_VOO));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_BILHETE));
        Assertions.assertEquals(1, obterQtdArquivosDir(DIR_BILHETE_VIP));
    }

}