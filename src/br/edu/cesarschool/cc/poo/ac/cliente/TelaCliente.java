package br.edu.cesarschool.cc.poo.ac.cliente;

import java.util.Scanner;

public class TelaCliente {
    private final ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
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
                    incluirCliente();
                    break;
                case 2:
                    alterarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    buscarCliente();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void incluirCliente() {
        System.out.println("Incluir novo cliente:");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Saldo de pontos: ");
        double saldoPontos = scanner.nextDouble();
        scanner.nextLine();

        Cliente cliente = new Cliente(cpf, nome, saldoPontos);
        String resultado = clienteMediator.incluir(cliente);
        if (resultado == null) {
            System.out.println("Cliente incluído com sucesso!");
        } else {
            System.out.println(resultado);
        }
    }

    private void alterarCliente() {
        System.out.print("Digite o CPF do cliente a ser alterado: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Editar dados do cliente: (deixe em branco para não alterar)");
            System.out.print("Nome (" + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            if (nome.isEmpty()) {
                nome = cliente.getNome();
            } else nome = nome;

            System.out.print("Saldo de pontos (" + cliente.getSaldoPontos() + "): ");
            String saldoInput = scanner.nextLine();
            double saldoPontos;
            if (saldoInput.isEmpty()) {
                saldoPontos = cliente.getSaldoPontos();
            } else {
                saldoPontos = Double.parseDouble(saldoInput);
            }
            Cliente updatedCliente = new Cliente(cpf, nome, saldoPontos);
            String resultado = clienteMediator.alterar(updatedCliente);
            if (resultado == null) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println(resultado);
            }
        }
    }

    private void excluirCliente() {
        System.out.print("Digite o CPF do cliente a ser excluído: ");
        String cpf = scanner.nextLine();
        String resultado = clienteMediator.excluir(cpf);
        if (resultado == null) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println(resultado);
        }
    }

    private void buscarCliente() {
        System.out.print("Digite o CPF do cliente para busca: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Cliente encontrado: ");
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Saldo de Pontos: " + cliente.getSaldoPontos());
        }
    }
}