package br.edu.cesarschool.cc.poo.ac.testes;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.Bilhete;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteMediator;
import br.edu.cesarschool.cc.poo.ac.passagem.BilheteVip;
import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;

public class TestesAc01 {
    private static final Cliente CLI1 = new Cliente("69025183514", "MARIO", 100);
    private static final Voo VOO1 = new Voo("REC", "GRU", "JJ", 1231);
    @Test
    public void testCliente() {
        Cliente cli = new Cliente("12345678901","MARCOS", 20);
        cli.creditarPontos(30);
        Assertions.assertEquals(cli.getSaldoPontos(), 50);
        cli.debitarPontos(40);
        Assertions.assertEquals(cli.getSaldoPontos(), 10);
    }
    @Test
    public void testBilhete1() {
        Bilhete bil = new Bilhete(null, null, 1200, 300, null);
        Assertions.assertEquals(900, bil.obterValorPago());
        Assertions.assertEquals(45, bil.obterValorPontuacao());
        BilheteVip bilVip = new BilheteVip(null, null, 1000, 200, null, 20);
        Assertions.assertEquals(48, bilVip.obterValorPontuacaoVip());
    }
    @Test
    public void testBilhete2() {
        LocalDateTime dh = LocalDateTime.parse("2200-01-01T00:00:00");
        Bilhete bil = new Bilhete(CLI1, VOO1, 1200, 300, dh);
        Assertions.assertEquals("690251835141231220011", bil.gerarNumero());
    }
    @Test
    public void testVoo() {
        Voo voo = new Voo("REC", "GRU", "JJ", 1211);
        Assertions.assertEquals("JJ1211", voo.obterIdVoo());
    }
    @Test
    public void testSingletonClienteMediator() {
        ClienteMediator med1 = ClienteMediator.obterInstancia();
        ClienteMediator med2 = ClienteMediator.obterInstancia();
        assertionsSingleton(ClienteMediator.class, med1, med2);
    }
    @Test
    public void testSingletonBilheteMediator() {
        BilheteMediator med1 = BilheteMediator.obterInstancia();
        BilheteMediator med2 = BilheteMediator.obterInstancia();
        assertionsSingleton(BilheteMediator.class, med1, med2);
    }
    @Test
    public void testSingletonVooMediator() {
        VooMediator med1 = VooMediator.obterInstancia();
        VooMediator med2 = VooMediator.obterInstancia();
        assertionsSingleton(VooMediator.class, med1, med2);
    }
    private void assertionsSingleton(Class<?> clazz, Object med1, Object med2) {
        Assertions.assertNotNull(med1);
        Assertions.assertTrue(med1 == med2);
        Constructor<?>[] consts = clazz.getDeclaredConstructors();
        Assertions.assertEquals(1, consts.length);
        Assertions.assertEquals(2, consts[0].getModifiers());
    }

}