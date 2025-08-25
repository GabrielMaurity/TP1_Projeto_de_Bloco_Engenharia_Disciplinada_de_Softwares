package br.com.infnet;

import java.util.List;
import java.util.Scanner;

public class App 
{
        public static void main(String[] args) {
            ProdutoService produtoService = new ProdutoService();
            Scanner scanner = new Scanner(System.in);

            int opcao = -1;
            while (opcao != 0) {
                System.out.println("\n--- CRUD de Produtos (Em Memória) ---");
                System.out.println("1. Adicionar Produto");
                System.out.println("2. Listar Produtos");
                System.out.println("3. Atualizar Produto");
                System.out.println("4. Deletar Produto");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        System.out.print("Nome do produto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Preço do produto: ");
                        double preco = scanner.nextDouble();
                        produtoService.criar(nome, preco);
                        System.out.println("Produto adicionado com sucesso!");
                        break;
                    case 2:
                        List<Produto> produtos = produtoService.listar();
                        if (produtos.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado.");
                        } else {
                            System.out.println("--- Lista de Produtos ---");
                            produtos.forEach(System.out::println);
                        }
                        break;
                    case 3:
                        System.out.print("ID do produto a ser atualizado: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Novo nome do produto: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo preço do produto: ");
                        double novoPreco = scanner.nextDouble();

                        if (produtoService.atualizar(idAtualizar, novoNome, novoPreco)) {
                            System.out.println("Produto atualizado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 4:
                        System.out.print("ID do produto a ser deletado: ");
                        int idDeletar = scanner.nextInt();
                        if (produtoService.deletar(idDeletar)) {
                            System.out.println("Produto deletado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
            scanner.close();
        }
    }

