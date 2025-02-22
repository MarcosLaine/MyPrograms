import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MainGrafo {

    static class Grafo {
        private int qtdVertices;
        private List<Integer>[] listaAdjacencia;


        @SuppressWarnings("unchecked")
        public Grafo(int qtdVertices) {
            this.qtdVertices = qtdVertices;
            listaAdjacencia = new ArrayList[qtdVertices + 1];
            for (int i = 1; i <= qtdVertices; i++) {
                listaAdjacencia[i] = new ArrayList<>();
            }
        }

        public void adicionarAresta(int origem, int destino) {
            listaAdjacencia[origem].add(destino);
        }


        public List<Integer> getSucessores(int vertice) {
            return listaAdjacencia[vertice];
        }

        public List<Integer> getPredecessores(int vertice) {
            List<Integer> predecessores = new ArrayList<>();
            for (int i = 1; i <= qtdVertices; i++) {
                if (listaAdjacencia[i].contains(vertice)) {
                    predecessores.add(i);
                }
            }
            return predecessores;
        }

        /**
         * Retorna o grau de saída do vértice (quantidade de arestas que saem dele).
         *
         * @param vertice Vértice para o qual o grau de saída será calculado.
         * @return Grau de saída.
         */
        public int grauSaida(int vertice) {
            return listaAdjacencia[vertice].size();
        }

        /**
         * Retorna o grau de entrada do vértice (quantidade de arestas que chegam nele).
         *
         * @param vertice Vértice para o qual o grau de entrada será calculado.
         * @return Grau de entrada.
         */
        public int grauEntrada(int vertice) {
            int count = 0;
            for (int i = 1; i <= qtdVertices; i++) {
                if (listaAdjacencia[i].contains(vertice)) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Método principal para leitura do arquivo, construção do grafo e exibição das informações
     * para o vértice informado pelo usuário.
     *
     * @param args Parâmetros de linha de comando (opcional).
     */
    public static void main(String[] args) {
        // Caminho do arquivo. Pode ser passado via argumento ou usado o default.
        String caminhoArquivo = "graph-test-100-1.txt";
        if (args.length > 0) {
            caminhoArquivo = args[0];
        }

        try {
            Scanner scanner = new Scanner(new File(caminhoArquivo));

            // Lê a primeira linha: quantidade de vértices e de arestas.
            int qtdVertices = scanner.nextInt();
            int qtdArestas = scanner.nextInt();

            // Cria o grafo.
            Grafo grafo = new Grafo(qtdVertices);

            // Lê cada uma das arestas e adiciona ao grafo.
            for (int i = 0; i < qtdArestas; i++) {
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();
                grafo.adicionarAresta(origem, destino);
            }
            scanner.close();

            // Solicita ao usuário que informe um vértice.
            Scanner input = new Scanner(System.in);
            System.out.print("Informe o vértice para obter as informações: ");
            int verticeInformado = input.nextInt();
            input.close();

            // Verifica se o vértice informado é válido.
            if (verticeInformado < 1 || verticeInformado > qtdVertices) {
                System.out.println("Vértice informado inválido. Deve estar entre 1 e " + qtdVertices);
                return;
            }

            // Obtém as informações para o vértice informado.
            int grauSaida = grafo.grauSaida(verticeInformado);
            int grauEntrada = grafo.grauEntrada(verticeInformado);
            List<Integer> sucessores = grafo.getSucessores(verticeInformado);
            List<Integer> predecessores = grafo.getPredecessores(verticeInformado);

            // Exibe as informações.
            System.out.println("Informações para o vértice " + verticeInformado + ":");
            System.out.println("Grau de saída: " + grauSaida);
            System.out.println("Grau de entrada: " + grauEntrada);
            System.out.println("Conjunto de sucessores: " + sucessores);
            System.out.println("Conjunto de predecessores: " + predecessores);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminhoArquivo);
        }
    }
}
