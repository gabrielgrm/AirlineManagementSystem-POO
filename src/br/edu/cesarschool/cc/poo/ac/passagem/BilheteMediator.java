package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

import java.time.LocalDateTime;

public class BilheteMediator {
    private static BilheteMediator instance;
    private final BilheteDAO bilheteDAO = new BilheteDAO();
    private final BilheteVipDAO bilheteVipDAO = new BilheteVipDAO();
    private final VooMediator vooMediator = VooMediator.obterInstancia();
    private final ClienteMediator clienteMediator = ClienteMediator.obterInstancia();

    private BilheteMediator() {}

    public static BilheteMediator obterInstancia() {
        if (instance == null) {
            instance = new BilheteMediator();
        }
        return instance;
    }

    public Bilhete buscar(String numeroBilhete) {
        return bilheteDAO.buscar(numeroBilhete);
    }

    public BilheteVip buscarVip(String numeroBilhete) {
        return bilheteVipDAO.buscar(numeroBilhete);
    }

    public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            return "CPF errado";
        }
        String ciaNumero = vooMediator.validarCiaNumero(ciaAerea, numeroVoo);
        if (ciaNumero != null) {
            return ciaNumero;
        }
        if (preco <= 0) {
            return "Preco errado";
        }
        if (pagamentoEmPontos < 0) {
            return "Pagamento pontos errado";
        }
        if (preco < pagamentoEmPontos) {
            return "Preco menor que pagamento em pontos";
        }
        if (dataHora.isBefore(LocalDateTime.now().plusHours(1))) {
            return "data hora invalida";
        }
        return null;
    }

    public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora){

        String erroValidacao = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (erroValidacao != null) {
            return new ResultadoGeracaoBilhete(null, null, erroValidacao);
        }


        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String vooId = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(vooId);

        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double pontosNecessarios = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < pontosNecessarios) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }


        Bilhete bilhete = new Bilhete(cliente, vooEncontrado, pagamentoEmPontos, preco, dataHora);


        cliente.creditarPontos(bilhete.obterValorPontuacao());
        cliente.debitarPontos(pontosNecessarios);

        boolean included = bilheteDAO.incluir(bilhete);
        if (!included) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }


        String erroAlteracao = clienteMediator.alterar(cliente);
        if (erroAlteracao != null) {
            return new ResultadoGeracaoBilhete(null, null, erroAlteracao);
        }

        return new ResultadoGeracaoBilhete(bilhete,null, null);

    }


    public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {

        String erroValidacao = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (erroValidacao != null) {
            return new ResultadoGeracaoBilhete(null, null, erroValidacao);
        }

        if (bonusPontuacao <= 0 || bonusPontuacao > 100) {
            return new ResultadoGeracaoBilhete(null, null, "Bonus errado");
        }


        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String vooId = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(vooId);

        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double pontosNecessarios = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < pontosNecessarios) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }


        BilheteVip bilheteVip = new BilheteVip(cliente, vooEncontrado, pagamentoEmPontos, preco, dataHora, bonusPontuacao);

        cliente.debitarPontos(pontosNecessarios);
        cliente.creditarPontos(bilheteVip.obterValorPontuacaoVip());

        boolean included = bilheteVipDAO.incluir(bilheteVip);
        if (!included) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }


        String erroAlteracao = clienteMediator.alterar(cliente);
        if (erroAlteracao != null) {
            return new ResultadoGeracaoBilhete(null, null, erroAlteracao);
        }

        return new ResultadoGeracaoBilhete(null, bilheteVip, null);
    }
}
