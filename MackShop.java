import java.util.Scanner;

public class MackShop {

    // Declarando vetores
    // Catálogo de produtos
    static int[] idsProdutos;
    static String[] nomesProdutos;
    static double[] precosProdutos;
    static int[] estoquesProdutos;

    // Venda atual
    static int[] vendaAtualIds;
    static int[] vendaAtualQuantidades;

    // Histórico de Vendas (vetores e matriz)
    static int[] historicoIdsPedidos;
    static double[] historicoValoresPedidos;
    static int[][] historicoItensVendidos;

    static int proximoIdPedido = 1001;
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        boolean baseInicializada = false;
        boolean iniciada = false;
        int opcao;

        do{
            menu();
            System.out.println("Digite uma opcao: ");
            opcao = entrada.nextInt();

            switch (opcao){
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
                /*case 5:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        finalizarVenda();
                        break;
                    }    
                case 6:
                    verHistoricoVendas();
                    break;
                case 7:
                    buscarVendaEspecifica();
                    break;
                case 8:
                    reporEstoque();
                    break;
                case 9:
                    relatorioEstoqueBaixo();
                    break;*/
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
        idsProdutos = new int[]{1, 2, 3, 4, 5};
        nomesProdutos = new String[]{"Mouse Gamer", "Teclado Mecanico", "Headset 7.1", "Monitor", "Notebook"};
        precosProdutos = new double[]{150.00, 350.00, 420.50, 600.00, 2000.00};
        estoquesProdutos = new int[]{15, 20, 10, 7, 3};
        vendaAtualIds = new int[0];
        vendaAtualQuantidades = new int[0];


        System.out.println("Base inicializada com sucesso! \nPROSSIGA:");
        return true;

    }

    public static void catalogoDeProdutos() {
        System.out.println("\n-------------- Catálogo de Produtos ---------------");
        System.out.printf("%-5s | %-20s | %-10s | %-10s\n", "ID", "Descrição", "Preço", "Estoque");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < idsProdutos.length; i++) {
            if (estoquesProdutos[i] > 0) {
                System.out.printf("%-5d | %-20s | R$ %-7.2f | %-10d\n", idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
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
            if (idsProdutos[i] == idProduto && estoquesProdutos[i] > 0){
                vendaAtualIds = new int[] {idProduto}; 
                vendaAtualQuantidades= new int[] {qtdProduto}; 
            } 
        
        int[] novosIds = new int[vendaAtualIds.length + 1];
        int[] novasQtds = new int[vendaAtualQuantidades.length + 1];

        for (int a = 0; a < vendaAtualIds.length; a++) {
            novosIds[a] = vendaAtualIds[a];
            novasQtds[a] = vendaAtualQuantidades[a];
        }

        novosIds[novosIds.length - 1] = idProduto;
        novasQtds[novasQtds.length - 1] = qtdProduto;

        vendaAtualIds = novosIds;
        vendaAtualQuantidades = novasQtds;
        }
    }
    public static void verResumoDaVendaAtual() {
        System.out.println("\n------------------- Resumo da Venda Atual -------------------------");
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-10s\n", "ID", "Descrição", "QTD", "VL. UNIT.", "VL. TOTAL");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < vendaAtualIds.length; i++){
            int idVenda = vendaAtualIds[i];
            int qtdVenda = vendaAtualQuantidades[i];
            double subtotal = qtdVenda * precosProdutos[i];

            System.out.printf("%-5d | %-20s | %-10d | %-10.2f1 | %-10.2f\n", idVenda, nomesProdutos[i], qtdVenda, precosProdutos[i], subtotal);
        }
       
    }

}