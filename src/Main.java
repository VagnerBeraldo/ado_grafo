import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        Grafo<String> grafo = new Grafo<String>();

        grafo.adicionarVertice("Buenos Aires");
        grafo.adicionarVertice("São Paulo");
        grafo.adicionarVertice("Miami");
        grafo.adicionarVertice("New York");
        grafo.adicionarVertice("Londres");
        grafo.adicionarVertice("Amsterdam");
        grafo.adicionarVertice("Johannesburg");
        grafo.adicionarVertice("Tóquio");
        grafo.adicionarVertice("Hong Kong");
        grafo.adicionarVertice("Sidney");

        grafo.adicionarAresta(2000.0, "Buenos Aires", "São Paulo");
        grafo.adicionarAresta(2000.0, "São Paulo", "Buenos Aires");
        grafo.adicionarAresta(6562.0, "São Paulo", "Miami");
        grafo.adicionarAresta(7424.0, "São Paulo", "Johannesburg");
        grafo.adicionarAresta(6562.0, "Miami", "São Paulo");
        grafo.adicionarAresta(1700.0, "Miami", "New York");
        grafo.adicionarAresta(1700.0, "New York", "Miami");
        grafo.adicionarAresta(5600.0, "New York", "Londres");
        grafo.adicionarAresta(5600.0, "Londres", "New York");
        grafo.adicionarAresta(10845.0, "New York", "Tóquio");
        grafo.adicionarAresta(10845.0, "Tóquio", "New York");
        grafo.adicionarAresta(7100.0, "Miami", "Londres");
        grafo.adicionarAresta(7100.0, "Londres", "Miami");
        grafo.adicionarAresta(9617.0, "Londres", "Hong Kong");
        grafo.adicionarAresta(9617.0, "Hong Kong", "Londres");
        grafo.adicionarAresta(360.0, "Londres", "Amsterdam");
        grafo.adicionarAresta(360.0, "Amsterdam", "Londres");
        grafo.adicionarAresta(9000.0, "Amsterdam", "Johannesburg");
        grafo.adicionarAresta(9000.0, "Johannesburg", "Amsterdam");
        grafo.adicionarAresta(11000.0, "Johannesburg", "Sidney");
        grafo.adicionarAresta(7424.0, "Johannesburg", "São Paulo");
        grafo.adicionarAresta(7800.0, "Sidney", "Tóquio");
        grafo.adicionarAresta(7800.0, "Tóquio", "Sidney");
        grafo.adicionarAresta(2900.0, "Hong Kong", "Tóquio");
        grafo.adicionarAresta(2900.0, "Tóquio", "Hong Kong");
        grafo.adicionarAresta(7400.0, "Hong Kong","Sidney");



        while (continuar) {
            System.out.println("Rede Mundial de Computadores");
            System.out.println("Menu:");
            System.out.println("1 Busca em Largura\t\t\t\t\t\t2 Busca em Profundidade");
            System.out.println("3 Menor Distância entre duas cidades");

            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine().trim();

            switch (opcao) {
                case "1":
                    System.out.println("Executando busca em largura...");
                    grafo.buscaEmLargura();
                    break;
                case "2":
                    System.out.println("Executando busca em profundidade...");
                    System.out.print("Digite o nome de uma cidade: ");
                    String exibirConexao = sc.nextLine();
                    grafo.exibirConexoes(exibirConexao);
                    break;
                case "3":
                    System.out.print("Digite a cidade de origem: ");
                    String origem = sc.nextLine();
                    System.out.print("Digite a cidade de destino: ");
                    String destino = sc.nextLine();
                    grafo.menorDistancia(origem, destino);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }

            String resposta = "";
            while (true) {
                System.out.println("\nDeseja continuar? (S/N)");
                resposta = sc.nextLine().trim().toUpperCase();

                if (resposta.equals("S")) {
                    break;
                } else if (resposta.equals("N")) {
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    break;
                } else {
                    System.out.println("Entrada inválida.\nDigite apenas 'S' para sim ou 'N' para não.");
                }
            }
        }
        sc.close();
    }
}
