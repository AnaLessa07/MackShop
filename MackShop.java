import java.util.Scanner;

public class MackShop {

    // Declarando vetores
    // Catálogo de produtos
    static int[] idsProdutos;
    static String[] nomesProdutos;
    static double[] precosProdutos;
    static int[] estoquesProdutos;

    // Venda atual
    static int[] vendaAtualIds = new int[100];
    static int[] vendaAtualQuantidades = new int[100];

    // Histórico de Vendas (vetores e matriz)
    static int[] historicoIdsPedidos;
    static double[] historicoValoresPedidos;
    static int[][] historicoItensVendidos;

    // Contadores
    static int contadorVendaAtual = 0;
    static int contadorHistorico = 0;

    static int proximoIdPedido = 1001;

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        boolean baseInicializada = false;
        boolean iniciada = false;
        int opcao;

        do {
            menu();
            System.out.println("Digite uma opcao: ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    iniciada = inicializarBase();
                    break;
                case 2:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        catalogoDeProdutos();
                        break;
                    }
                case 3:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        adicionarItemVenda();
                        break;
                    }
                case 4:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        verResumoDaVendaAtual();
                        break;
                    }
                case 5:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        finalizarVenda();
                        break;
                    }
                    /*
                     * case 6:
                     * verHistoricoVendas();
                     * break;
                     * case 7:
                     * buscarVendaEspecifica();
                     * break;
                     * case 8:
                     * reporEstoque();
                     * break;
                     * case 9:
                     * relatorioEstoqueBaixo();
                     * break;
                     */
                case 10:
                    System.out.println("\nSAINDO DO SISTEMA. OBRIGADA!");

            }
        } while (opcao != 10);
        entrada.close();
    }

    public static void menu() {
        System.out.print("""

                ###################### MENU #######################
                1. Inicializar base
                2. Exibir catálogo de produtos
                3. Adicionar item à venda
                4. Ver resumo da venda atual
                5. Finalizar venda (gerar histórico e Nota Fiscal)
                6. Ver histórico de vendas
                7. Buscar venda específica do histórico
                8. (Admin) Repor estoque
                9. (Admin) Relatório de estoque baixo
                10. Sair do programa
                ###################################################
                """);
    }

    public static Boolean inicializarBase() {
        idsProdutos = new int[] { 1, 2, 3, 4, 5 };
        nomesProdutos = new String[] { "Mouse Gamer", "Teclado Mecanico", "Headset 7.1", "Monitor", "Notebook" };
        precosProdutos = new double[] { 150.00, 350.00, 420.50, 600.00, 2000.00 };
        estoquesProdutos = new int[] { 15, 20, 10, 7, 3 };
        historicoIdsPedidos = new int[100];
        historicoValoresPedidos = new double[100];
        historicoItensVendidos = new int[100][3]; // linha: [idPedido, idProduto, qtd]

        System.out.println("Base inicializada com sucesso! \nPROSSIGA:");
        return true;

    }

    public static void catalogoDeProdutos() {
        System.out.println("\n-------------- Catálogo de Produtos ---------------");
        System.out.printf("%-5s | %-20s | %-10s | %-10s\n", "ID", "Descrição", "Preço", "Estoque");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < idsProdutos.length; i++) {
            if (estoquesProdutos[i] > 0) {
                System.out.printf("%-5d | %-20s | R$ %-7.2f | %-10d\n", idsProdutos[i], nomesProdutos[i],
                        precosProdutos[i], estoquesProdutos[i]);
            }
        }
    }

    public static void adicionarItemVenda() {
        int idProduto;
        int qtdProduto;
        System.out.println("Digite o ID do produto que quer adicionar ao carrinho: ");
        idProduto = entrada.nextInt();
        System.out.println("Digite a quantidade do produto add: ");
        qtdProduto = entrada.nextInt();

        for (int i = 0; i < idsProdutos.length; i++) {
            if (idsProdutos[i] == idProduto && estoquesProdutos[i] > 0 && qtdProduto <= estoquesProdutos[i]) {
                vendaAtualIds[contadorVendaAtual] = idProduto;
                vendaAtualQuantidades[contadorVendaAtual] = qtdProduto;
                contadorVendaAtual++;

                System.out.println("Produto adicionado no carrinho.");
                return;
            }
        }
    }

    public static void verResumoDaVendaAtual() {
        double total = 0;
        System.out.println("\n------------------- Resumo da Venda Atual -------------------------");
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-10s\n", "ID", "Descrição", "QTD", "VL. UNIT.", "VL. TOTAL");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < contadorVendaAtual; i++) {
            int idVenda = vendaAtualIds[i];
            int qtdVenda = vendaAtualQuantidades[i];
            double subtotal = qtdVenda * precosProdutos[idVenda - 1];
            total += subtotal;
            System.out.printf("%-5d | %-20s | %-10d | %-10.2f | %-10.2f\n", idVenda, nomesProdutos[idVenda - 1],
                    qtdVenda, precosProdutos[idVenda - 1], subtotal);
        }
        System.out.printf("%-55s %.2f\n", "TOTAL DA VENDA ATUAL:", total);

    }

    public static void finalizarVenda() {
        int idPedido = proximoIdPedido++;
        double totalVenda = 0;

        for (int i = 0; i < contadorVendaAtual; i++) {
            int idProduto = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];
            double subtotal = qtd * precosProdutos[idProduto - 1];
            totalVenda += subtotal;

            estoquesProdutos[idProduto - 1] -= qtd;

            // Salva no histórico de itens
            historicoItensVendidos[contadorHistorico][0] = idPedido; // pedido
            historicoItensVendidos[contadorHistorico][1] = idProduto; // produto
            historicoItensVendidos[contadorHistorico][2] = qtd; // quantidade
            contadorHistorico++;
        }

        // 2. Salva ID e valor total da venda
        historicoIdsPedidos[idPedido - 1001] = idPedido;
        historicoValoresPedidos[idPedido - 1001] = totalVenda;

        // 3. Imprime nota fiscal
        imprimirNotaFiscal(idPedido, totalVenda);

        // 4. Limpa carrinho
        contadorVendaAtual = 0;

        System.out.println("Venda finalizada com sucesso! Pedido " + idPedido);

    }

    public static void imprimirNotaFiscal(int idPedido, double totalVenda) {
        System.out.println(
                "*********************************************************************************************");
        System.out.println(
                "* MACKSHOP                                                                                  *");
        System.out.println(
                "* CNPJ: 12.345.678/0001-99                                                                  *");
        System.out.println(
                "*********************************************************************************************");
        System.out.printf(
                "* NOTA FISCAL - VENDA AO CONSUMIDOR                                                         *\n");
        System.out.printf(
                "* Pedido ID: %-30d                                                                          *\n", idPedido);
        System.out.println(
                "* Data de Emissão: 25/09/2025  20:40:00                                                     *");
        System.out.println(
                "*********************************************************************************************");
        System.out.println(
                "* ID   | DESCRIÇÃO            | QTD  | VL. UNIT.   | VL. TOTAL                              *");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        double valorTotal = 0;
        double subTotal = 0;
        for (int i = 0; i < contadorVendaAtual; i++) {
            int idProd = vendaAtualIds[i];
            int qtd = vendaAtualQuantidades[i];
            double valorUnit = precosProdutos[idProd - 1];
            subTotal = valorUnit * qtd;
            valorTotal += subTotal;

            System.out.printf("* %-4d | %-20s | %-4d | R$ %-8.2f | R$ %-10.2f                   *\n", idProd, nomesProdutos[idProd - 1], qtd, valorUnit, valorTotal);
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.printf(
                "* SUBTOTAL: R$ %.2f                                                                         *\n", subTotal);
        System.out.printf(
                "* TOTAL: R$ %.2f                                                                            *\n", valorTotal);
        System.out.println(
                "********************************************************************************************");
        System.out.println(
                "* OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE!                                                  *");
        System.out.println(
                "*********************************************************************************************");

    }

}