package br.edu.cesarschool.cc.poo.telas.modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Anônimo
 *
 * Trata-se de uma implementação de tela primária, para efeito didático e de demonstração simplificada
 * da arquitetura em camadas e do custo de implementação de uma interface visual, por mais simples que
 * seja!
 *
 */
public class TelaProduto {

    private static final String DIGITE_O_CODIGO = "Digite o código: ";
    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado!";
    private static final int CODIGO_DESCONHECIDO = -1;
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));
    private ProdutoMediator produtoMediator = new ProdutoMediator();

    public void inicializaTelasCadastroProduto() {
        while(true) {
            long codigo = CODIGO_DESCONHECIDO;
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            if (opcao == 1) {
                processaInclusao();
            } else if (opcao == 2) {
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaAlteracao(codigo);
                }
            } else if (opcao == 3) {
                codigo = processaBusca();
                if (codigo != CODIGO_DESCONHECIDO) {
                    processaExclusao(codigo);
                }
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de produtos");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void processaInclusao() {
        Produto produto = capturaProduto(CODIGO_DESCONHECIDO);
        String retorno = produtoMediator.incluir(produto);
        if (retorno == null) {
            System.out.println("Produto incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaAlteracao(long codigo) {
        Produto produto = capturaProduto(codigo);
        String retorno = produtoMediator.alterar(produto);
        if (retorno == null) {
            System.out.println("Produto alterado com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private long processaBusca() {
        System.out.print(DIGITE_O_CODIGO);
        long codigo = ENTRADA.nextLong();
        Produto produto = produtoMediator.buscar(codigo);
        if (produto == null) {
            System.out.println(PRODUTO_NAO_ENCONTRADO);
            return CODIGO_DESCONHECIDO;
        } else {
            // Mostrar todos os atributos do Produto!!
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Nome: " + produto.getNome());
            return codigo;
        }
    }

    private void processaExclusao(long codigo) {
        String retorno = produtoMediator.excluir(codigo);
        if (retorno == null) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private Produto capturaProduto(long codigoDaAlteracao) {
        Produto produto = null;
        long codigo;
        if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
            System.out.print(DIGITE_O_CODIGO);
            codigo = ENTRADA.nextLong();
        } else {
            codigo = codigoDaAlteracao;
        }
        // Lê todos os atributos do Produto
        System.out.print("Digite o nome: ");
        String nome = lerString();
        // Cria instância de Produto com todos os atributos
        produto = new Produto(codigo, nome);
        return produto;
    }
    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}