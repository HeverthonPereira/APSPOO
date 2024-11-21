/*
- Encontre o erro e faça a correção
- Construa um menu para melhorar a aplicação
- Seria ideal que cada cliente, produdo e pedido tivessem um codigo único
- Possibilitar o cancelamento do item do Produto, voltando o produto para o estoque
- Possibilitar o cancelamento da venda, voltando o produto para o estoque

*/

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<ItemPedido> itensPedido = new ArrayList<>();

        int opcao;
        do {
            exibirMenu();
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scan, clientes);
                    break;
                case 2:
                    cadastrarProduto(scan, produtos);
                    break;
                case 3:
                    listarClientes(clientes);
                    break;
                case 4:
                    listarProdutos(produtos);
                    break;
                case 5:
                    cadastrarPedido(scan, clientes, produtos, pedidos, itensPedido);
                    break;
                case 0:
                    System.out.println("Saindo da  Aplicação");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("┌───────────────────────────┐");
        System.out.println("│     Aplicação Vendas      │");
        System.out.println("│  1 - Cadastro de Cliente  │");
        System.out.println("│  2 - Cadastro de Produto  │");
        System.out.println("│  3 - Listar Clientes      │");
        System.out.println("│  4 - Listar Produtos      │");
        System.out.println("│  5 - Cadastrar Pedido     │");
        System.out.println("│  0 - Sair                 │");
        System.out.println("└───────────────────────────┘");
        System.out.print("Digite sua opção: ");
    }

    private static void cadastrarCliente(Scanner scan, ArrayList<Cliente> clientes) {
        scan.nextLine();
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("CPF: ");
        String cpf = scan.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarProduto(Scanner scan, ArrayList<Produto> produtos) {
        scan.nextLine();
        System.out.print("Nome do Produto: ");
        String nomeProduto = scan.nextLine();

        System.out.print("Preço do Produto: ");
        double precoProduto = scan.nextDouble();

        System.out.print("Quantidade em Estoque: ");
        int quantidadeProduto = scan.nextInt();

        Produto produto = new Produto(nomeProduto, precoProduto, quantidadeProduto);
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarClientes(ArrayList<Cliente> clientes) {
        System.out.println("Clientes cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome() + " | CPF: " + clientes.get(i).getCpf());
        }
    }

    private static void listarProdutos(ArrayList<Produto> produtos) {
        System.out.println("Produtos cadastrados:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " - " + produtos.get(i).getNomeProduto() + " | Preço: " + produtos.get(i).getPrecoProduto() + " | Quantidade: " + produtos.get(i).getQuantidadeProduto());
        }
    }

    private static void cadastrarPedido(Scanner scan, ArrayList<Cliente> clientes, ArrayList<Produto> produtos, ArrayList<Pedido> pedidos, ArrayList<ItemPedido> itensPedido) {
        listarClientes(clientes);
        System.out.print("Digite o código do cliente: ");
        int codigoCliente = scan.nextInt();

        Cliente clienteEscolhido = clientes.get(codigoCliente);

        System.out.print("Número do pedido: ");
        int numeroPedido = scan.nextInt();

        Pedido pedido = new Pedido(clienteEscolhido, numeroPedido);
        pedidos.add(pedido);

        boolean adicionarMaisItens = true;
        while (adicionarMaisItens) {
            listarProdutos(produtos);
            System.out.print("Digite o código do produto: ");
            int codigoProduto = scan.nextInt();

            Produto produtoEscolhido = produtos.get(codigoProduto);

            System.out.print("Quantidade do produto: ");
            int quantidadeProduto = scan.nextInt();

            if (produtoEscolhido.getQuantidadeProduto() >= quantidadeProduto) {


                ItemPedido item = new ItemPedido(pedido, produtoEscolhido, quantidadeProduto);
                itensPedido.add(item);

                produtoEscolhido.setQuantidadeProduto(produtoEscolhido.getQuantidadeProduto() - quantidadeProduto);
                System.out.println("Produto adicionado ao pedido com sucesso!");

                System.out.print("Deseja adicionar mais itens ao pedido? (1 - Sim, 0 - Não): ");
                int opcao = scan.nextInt();
                adicionarMaisItens = opcao == 1;
            } else {
                System.out.println("Quantidade insuficiente em estoque para este produto.");
            }
        }
    }

    private static void listarPedidos(ArrayList<ItemPedido> itensPedido) {
        System.out.println("Pedidos realizados:");
        if (itensPedido.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
        } else {

            for (ItemPedido item : itensPedido) {
                Pedido pedido = item.getPedido();
                System.out.println("Pedido Número: " + pedido.getNumPedido());
                System.out.println("Cliente: " + pedido.getCliente().getNome());
                System.out.println("CPF do Cliente: " + pedido.getCliente().getCpf());
                item.mostrarItemPedido(); 
                System.out.println("───────────────────────────");
            }
        }
    }
}