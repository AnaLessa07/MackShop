import java.util.Scanner;

public class MackShop {

    // Declarando 
    // Catálogo de produtos (vetores paralelos)
    static int[] idsProdutos;
    static String[] nomesProdutos;
    static double[] precosProdutos;
    static int[] estoquesProdutos;
    static int totalProdutosCadastrados = 0;

    // Venda atual (vetores paralelos)
    static int[] vendaAtualIds;
    static int[] vendaAtualQuantidades;
    static int vendaAtualTamanho = 0;

    // Histórico de Vendas (vetores e matriz)
    static int[] historicoIdsPedidos;
    static double[] historicoValoresPedidos;
    static int historicoTamanho = 0;
    static int[][] historicoItensVendidos;
    static int historicoItensTamanho = 0;

    static int proximoIdPedido = 1001;
    static boolean baseInicializada = false;
    static Scanner entrada = new Scanner(System.in);
    static boolean iniciada = false;

    public static void main(String[] args) {
        int opcao;

        do{
            System.out.println("""

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

            System.out.println("Digite uma opcao: ");
            opcao = entrada.nextInt();

            switch (opcao){
                case 1: 
                    inicializarBase();
                    break;
                case 2:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        catalogoDeProdutos();
                    }
                /* case 3:
                    if (iniciada == false) {
                        System.out.println("Base nao iniciada, voce deve digitar a opção 1 para comecar");
                        break;
                    } else {
                        adicionarItemVenda();
                    }
                case 4:
                    verResumoDaVendaAtual();
                    break;
                case 5:
                    finalizarVenda();
                    break;
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
                    break;
                default:
                System.out.println("NUMERO INVALIDO, RENICIE O PROGRAMA");
                    
            }*/
        } while (opcao != 10)
        System.out.println("Saindo do programa. Obrigado!");
        break;
    } 

    public static void inicializarBase() {
        idsProdutos = new int[]{1, 2, 3, 4, 5};
        nomesProdutos = new String[]{"Mouse Gamer", "Teclado Mecanico", "Headset 7.1", "Monitor", "Notebook"};
        precosProdutos = new double[]{150.00, 350.00, 420.50, 600.00, 2000.00};
        estoquesProdutos = new int[]{15, 20, 10, 7, 3};

        baseInicializada = true;
        System.out.println("Base inicializada com sucesso! \n PROSSIGA:");

    }
    public static void catalogoDeProdutos() {
        System.out.println("\n--- Catálogo de Produtos ---");
        System.out.printf("%-5s | %-20s | %-10s | %-10s\n", "ID", "Descrição", "Preço", "Estoque");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < totalProdutosCadastrados; i++) {
            if (estoquesProdutos[i] > 0) {
                System.out.printf("%-5d | %-20s | R$ %-7.2f | %-10d\n", idsProdutos[i], nomesProdutos[i], precosProdutos[i], estoquesProdutos[i]);
            }
        }
    }

}