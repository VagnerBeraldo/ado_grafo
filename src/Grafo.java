import java.util.ArrayList;

public class Grafo<TIPO>{
    private ArrayList<Vertice<TIPO>> vertice;
    private ArrayList<Aresta<TIPO>> aresta;

    public Grafo(){
        this.vertice = new ArrayList<Vertice<TIPO>>();
        this.aresta = new ArrayList<Aresta<TIPO>>();
    }

    public void adicionarVertice(TIPO dado){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertice.add(novoVertice);
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.aresta.add(aresta);
    }


    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i = 0; i < this.vertice.size(); i++){
            if(this.vertice.get(i).getDado().equals(dado)){
                vertice = this.vertice.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura(){
        ArrayList<Vertice<TIPO>> marcado = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertice.get(0);
        marcado.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<TIPO> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestaSaida().size(); i++) {
                Vertice<TIPO> proximo = visitado.getArestaSaida().get(i).getFim();
                if(!marcado.contains(proximo)){
                    marcado.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }


    public void menorDistancia(TIPO origem, TIPO destino) {
        int tamanho = vertice.size();
        double[] distancias = new double[tamanho];
        boolean[] visitados = new boolean[tamanho];
        int[] anteriores = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            distancias[i] = Double.MAX_VALUE;
            visitados[i] = false;
            anteriores[i] = -1;
        }

        Vertice<TIPO> vOrigem = getVertice(origem);
        Vertice<TIPO> vDestino = getVertice(destino);
        int indiceOrigem = vertice.indexOf(vOrigem);
        int indiceDestino = vertice.indexOf(vDestino);

        if (indiceOrigem == -1 || indiceDestino == -1) {
            System.out.println("Cidade(s) não encontrada(s).");
            return;
        }

        distancias[indiceOrigem] = 0;

        for (int i = 0; i < tamanho; i++) {
            // Encontrar o vértice não visitado com menor distância
            int atual = -1;
            double menorDistancia = Double.MAX_VALUE;
            for (int j = 0; j < tamanho; j++) {
                if (!visitados[j] && distancias[j] < menorDistancia) {
                    menorDistancia = distancias[j];
                    atual = j;
                }
            }

            if (atual == -1) break; // Não há mais vértices acessíveis
            visitados[atual] = true;

            // Atualizar distâncias dos vizinhos
            Vertice<TIPO> verticeAtual = vertice.get(atual);
            for (Aresta<TIPO> aresta : verticeAtual.getArestaSaida()) {
                Vertice<TIPO> vizinho = aresta.getFim();
                int indiceVizinho = vertice.indexOf(vizinho);
                double novaDistancia = distancias[atual] + aresta.getPeso();

                if (novaDistancia < distancias[indiceVizinho]) {
                    distancias[indiceVizinho] = novaDistancia;
                    anteriores[indiceVizinho] = atual;
                }
            }
        }

        if (distancias[indiceDestino] == Double.MAX_VALUE) {
            System.out.println("Não há caminho entre as cidades.");
            return;
        }

        // Reconstruir caminho
        ArrayList<Vertice<TIPO>> caminho = new ArrayList<>();
        int atual = indiceDestino;
        while (atual != -1) {
            caminho.add(0, vertice.get(atual));
            atual = anteriores[atual];
        }

        System.out.print("Menor caminho: ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i).getDado());
            if (i < caminho.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\nDistância total: " + distancias[indiceDestino] + " Km");
    }


    public void exibirConexoes(TIPO cidade) {
        Vertice<TIPO> origem = getVertice(cidade);
        if (origem == null) {
            System.out.println("Cidade não encontrada no grafo.");
            return;
        }

        System.out.println("Conexões diretas de " + cidade + ":");
        for (Aresta<TIPO> aresta : origem.getArestaSaida()) {
            System.out.println("-> " + aresta.getFim().getDado());
        }
    }


}

