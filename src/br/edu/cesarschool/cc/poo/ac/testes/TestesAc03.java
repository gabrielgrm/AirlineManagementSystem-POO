package br.edu.cesarschool.cc.poo.ac.testes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cesarschool.cc.poo.ac.passagem.Voo;
import br.edu.cesarschool.cc.poo.ac.passagem.VooMediator;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class TestesAc03 extends TesteGeral {
    private static final String VOO_INEXISTENTE = "Voo inexistente";
    private static final String PNZ = "PNZ";
    private static final String VCP = "VCP";
    private static final String CIA_AEREA_ERRADA = "CIA aerea errada";
    private static final String AEROPORTO_DESTINO_ERRADO = "Aeroporto destino errado";
    private static final String AEROPORTO_ORIGEM_ERRADO = "Aeroporto origem errado";
    private VooMediator vooMed = VooMediator.obterInstancia();
    private CadastroObjetos cadastroVoo = new CadastroObjetos(Voo.class);

    @Test
    public void testCadVoo1() {
        excluirCadastros();
        Voo voo = new Voo("", "GRU", "JJ", 1234);
        Assertions.assertEquals(AEROPORTO_ORIGEM_ERRADO, vooMed.validar(voo));
        voo = new Voo(null, "REC", "GA", 2222);
        Assertions.assertEquals(AEROPORTO_ORIGEM_ERRADO, vooMed.incluir(voo));
        voo = new Voo("ZZZ", "MCZ", "LI", 1111);
        Assertions.assertEquals(AEROPORTO_ORIGEM_ERRADO, vooMed.alterar(voo));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
    }
    @Test
    public void testCadVoo2() {
        excluirCadastros();
        Voo voo = new Voo("MAO", "", "XZ", 9878);
        Assertions.assertEquals(AEROPORTO_DESTINO_ERRADO, vooMed.incluir(voo));
        voo = new Voo("VIX", null, "AZ", 7654);
        Assertions.assertEquals(AEROPORTO_DESTINO_ERRADO, vooMed.alterar(voo));
        voo = new Voo("CWB", "LIS", "FF", 5544);
        Assertions.assertEquals(AEROPORTO_DESTINO_ERRADO, vooMed.validar(voo));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
    }
    @Test
    public void testCadVoo3() {
        excluirCadastros();
        String poa = "POA";
        Voo voo = new Voo(poa, poa, "AA", 2255);
        Assertions.assertEquals("Aeroporto origem igual a aeroporto destino", vooMed.incluir(voo));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
    }
    @Test
    public void testCadVoo4() {
        excluirCadastros();
        Voo voo = new Voo("SLZ", "BEL", "", 2255);
        Assertions.assertEquals(CIA_AEREA_ERRADA, vooMed.incluir(voo));
        voo = new Voo("GIG", "VIX", null, 1122);
        Assertions.assertEquals(CIA_AEREA_ERRADA, vooMed.validar(voo));
        voo = new Voo("SDU", "CGH", "JJA", 8745);
        Assertions.assertEquals(CIA_AEREA_ERRADA, vooMed.alterar(voo));
        Assertions.assertEquals("Numero voo errado", vooMed.validarCiaNumero("IU", 123456));
        Assertions.assertEquals(0, obterQtdArquivosDir(DIR_VOO));
    }
    @Test
    public void testCadVoo5() {
        excluirCadastros();
        String cia = "CZ";
        int num = 3232;
        Voo voo = new Voo("CNF", "SSA", cia, num);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Voo vooDup = new Voo("GYN", "FEN", cia, num);
        Assertions.assertEquals("Voo ja existente", vooMed.incluir(vooDup));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(1, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(voo.obterIdVoo());
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(voo, vooOri));
    }
    @Test
    public void testCadVoo6() {
        excluirCadastros();
        String cia = "ZX";
        Voo voo = new Voo("FLN", "RAO", cia, 6666);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Voo vooNew = new Voo("PMW", "CAU", cia, 6667);
        Assertions.assertEquals(null, vooMed.incluir(vooNew));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(2, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(vooNew.obterIdVoo());
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(vooNew, vooOri));
    }

    @Test
    public void testCadVoo7() {
        excluirCadastros();
        String cia = "TW";
        Voo voo = new Voo("BVB", "PVH", cia, 8888);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Voo vooAlt = new Voo("RBR", "THE", cia, 8086);
        Assertions.assertEquals(VOO_INEXISTENTE, vooMed.alterar(vooAlt));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(1, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(voo.obterIdVoo());
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(voo, vooOri));
    }
    @Test
    public void testCadVoo8() {
        excluirCadastros();
        String cia = "YP";
        int num = 8088;
        Voo voo = new Voo(VCP, PNZ, cia, num);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Voo vooAlt = new Voo("FOR", "SET", cia, num);
        Assertions.assertEquals(null, vooMed.alterar(vooAlt));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(1, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(voo.obterIdVoo());
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(vooAlt, vooOri));
    }
    @Test
    public void testCadVoo9() {
        String idVoo = "KI4511";
        excluirCadastros();
        Voo voo = new Voo(VCP, PNZ, "KI", 4500);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Assertions.assertEquals(VOO_INEXISTENTE, vooMed.excluir(idVoo));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(1, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(voo.obterIdVoo());
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(voo, vooOri));
    }
    @Test
    public void testCadVoo10() {
        String idVoo = "PL4501";
        excluirCadastros();
        Voo voo = new Voo(VCP, PNZ, "PL", 4501);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Assertions.assertEquals(null, vooMed.excluir(idVoo));
        int qtdArqsVoo = obterQtdArquivosDir(DIR_VOO);
        Assertions.assertEquals(0, qtdArqsVoo);
        Voo vooOri = (Voo)cadastroVoo.buscar(idVoo);
        Assertions.assertNull(vooOri);
    }
    @Test
    public void testCadVoo11() {
        String idVoo = "QW4444";
        excluirCadastros();
        Voo voo = new Voo(VCP, PNZ, "QW", 5566);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Assertions.assertNull(vooMed.buscar(idVoo));
    }
    @Test
    public void testCadVoo12() {
        String idVoo = "BA1123";
        excluirCadastros();
        Voo voo = new Voo(VCP, PNZ, "BA", 1123);
        cadastroVoo.incluir(voo, voo.obterIdVoo());
        Voo vooOri = vooMed.buscar(idVoo);
        Assertions.assertNotNull(vooOri);
        Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(voo, vooOri));
    }
}