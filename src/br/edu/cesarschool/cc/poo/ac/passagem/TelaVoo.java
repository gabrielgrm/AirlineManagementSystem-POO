package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;

import java.util.Scanner;

public class TelaVoo {
    public TelaVoo() {}

    private VooMediator vooMediator = VooMediator.obterInstancia();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Menu Principal:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    incluirVoo();
                    break;
                case 2:
                    alterarVoo();
                    break;
                case 3:
                    excluirVoo();
                    break;
                case 4:
                    buscarVoo();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void incluirVoo() {
        System.out.println("Incluir novo voo:");
        System.out.print("Numero Voo: ");
        int numeroVoo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Aeroporto Origem: ");
        String aeroportoOrigem = scanner.nextLine();
        System.out.print("Aeroporto Destino: ");
        String aeroportoDestino = scanner.nextLine();
        System.out.print("Companhia Aérea: ");
        String companhiaAerea = scanner.nextLine();


        Voo voo = new Voo(aeroportoOrigem, aeroportoDestino, companhiaAerea, numeroVoo);
        String resultado = vooMediator.incluir(voo);
        if (resultado == null) {
            System.out.println("Voo incluído com sucesso!");
        } else {
            System.out.println(resultado);
        }
    }

    private void alterarVoo() {
        System.out.print("Digite o número do Voo a ser alterado: ");
        int numeroVoo = scanner.nextInt();
        Voo voo = vooMediator.buscar(String.valueOf(numeroVoo));
        if (voo == null) {
            System.out.println("Voo não encontrado.");
        } else {
            System.out.println("Editar dados do voo: (deixe em branco para não alterar)");
            System.out.print("Aeroporto Origem (" + voo.getAeroportoOrigem() + "): ");
            String aeroportoOrigem = scanner.nextLine();
            if (aeroportoOrigem.isEmpty()) {
                aeroportoOrigem = voo.getAeroportoOrigem();
            } else aeroportoOrigem = aeroportoOrigem;

            System.out.print("Aeroporto Destino (" + voo.getAeroportoDestino() + "): ");
            String aeroportoDestino = scanner.nextLine();
            if (aeroportoDestino.isEmpty()) {
                aeroportoDestino = voo.getAeroportoDestino();
            } else aeroportoDestino = aeroportoDestino;

            System.out.print("Companhia Áerea (" + voo.getCompanhiaAerea() + "): ");
            String companhiaAerea = scanner.nextLine();
            if (companhiaAerea.isEmpty()) {
                companhiaAerea = voo.getCompanhiaAerea();
            } else companhiaAerea = companhiaAerea;

            Voo updatedVoo = new Voo(aeroportoOrigem, aeroportoDestino, companhiaAerea, numeroVoo);
            String resultado = vooMediator.alterar(updatedVoo);
            if (resultado == null) {
                System.out.println("Voo alterado com sucesso!");
            } else {
                System.out.println(resultado);
            }
        }
    }

    private void excluirVoo() {
        System.out.print("Digite o número do voo a ser excluído: ");
        String numeroVoo = scanner.nextLine();
        String resultado = vooMediator.excluir(numeroVoo);
        if (resultado == null) {
            System.out.println("Voo excluído com sucesso!");
        } else {
            System.out.println(resultado);
        }
    }

    private void buscarVoo() {
        System.out.print("Digite o número do Voo para busca: ");
        String numeroVoo = scanner.nextLine();
        Voo voo = vooMediator.buscar(numeroVoo);
        if (voo == null) {
            System.out.println("Voo não encontrado.");
        } else {
            System.out.println("Voo encontrado: ");
            System.out.println("Número do Voo: " + voo.getNumeroVoo());
            System.out.println("Aeroporto Origem: " + voo.getAeroportoOrigem());
            System.out.println("Aeroporto Destino: " + voo.getAeroportoDestino());
            System.out.println("Companhia aérea: " + voo.getCompanhiaAerea());
        }
    }
}
