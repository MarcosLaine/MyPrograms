import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BasicOfGraph {

    // Classe que representa um grafo
    static class Grafo {
        private int qtdVertices; // Número de vértices no grafo
        private List<Integer>[] listaAdjacencia; // Lista de adjacência para armazenar as arestas

        // Construtor que inicializa o grafo com um número específico de vértices
        @SuppressWarnings("unchecked")
        public Grafo(int qtdVertices) {
            this.qtdVertices = qtdVertices;
            listaAdjacencia = new ArrayList[qtdVertices + 1]; // +1 para usar índices a partir de 1
            for (int i = 1; i <= qtdVertices; i++) {
                listaAdjacencia[i] = new ArrayList<>(); // Inicializa a lista de adjacência para cada vértice
            }
        }

        // Método para adicionar uma aresta entre dois vértices
        public void adicionarAresta(int origem, int destino) {
            listaAdjacencia[origem].add(destino); // Adiciona o destino à lista de adjacência do vértice de origem
        }

        // Método para obter os sucessores de um vértice
        public List<Integer> getSucessores(int vertice) {
            return listaAdjacencia[vertice]; // Retorna a lista de sucessores do vértice
        }

        // Método para obter os predecessores de um vértice
        public List<Integer> getPredecessores(int vertice) {
            List<Integer> predecessores = new ArrayList<>();
            for (int i = 1; i <= qtdVertices; i++) {
                if (listaAdjacencia[i].contains(vertice)) { // Verifica se o vértice está na lista de adjacência
                    predecessores.add(i); // Adiciona o vértice à lista de predecessores
                }
            }
            return predecessores; // Retorna a lista de predecessores
        }

        // Método para calcular o grau de saída de um vértice
        public int grauSaida(int vertice) {
            return listaAdjacencia[vertice].size(); // Retorna o tamanho da lista de adjacência (número de arestas que saem)
        }

        // Método para calcular o grau de entrada de um vértice
        public int grauEntrada(int vertice) {
            int count = 0;
            for (int i = 1; i <= qtdVertices; i++) {
                if (listaAdjacencia[i].contains(vertice)) { // Verifica se o vértice é um destino em alguma aresta
                    count++; // Incrementa o contador
                }
            }
            return count; // Retorna o grau de entrada
        }

        // Método para realizar a busca em profundidade (DFS)
        public List<Integer> BuscaEmProfundidade(int verticeInicial) {
            boolean[] visitado = new boolean[qtdVertices + 1]; // Array para marcar vértices visitados
            List<Integer> caminho = new ArrayList<>(); // Lista para armazenar o caminho percorrido
            BuscaEmProfundidadeUtil(verticeInicial, visitado, caminho); // Chama o método auxiliar
            return caminho; // Retorna o caminho encontrado
        }

        // Método auxiliar para a busca em profundidade
        private void BuscaEmProfundidadeUtil(int vertice, boolean[] visitado, List<Integer> caminho) {
            visitado[vertice] = true; // Marca o vértice como visitado
            caminho.add(vertice); // Adiciona o vértice ao caminho
            for (int sucessor : listaAdjacencia[vertice]) { // Itera sobre os sucessores
                if (!visitado[sucessor]) { // Se o sucessor não foi visitado
                    BuscaEmProfundidadeUtil(sucessor, visitado, caminho); // Chama recursivamente para o sucessor
                }
            }
        }   

        // Método para realizar a busca em largura (BFS)
        public List<Integer> BuscaEmLargura(int verticeInicial) {
            boolean[] visitado = new boolean[qtdVertices + 1]; // Array para marcar vértices visitados
            List<Integer> caminho = new ArrayList<>(); // Lista para armazenar o caminho percorrido
            Queue<Integer> fila = new LinkedList<>(); // Fila para a BFS
            visitado[verticeInicial] = true; // Marca o vértice inicial como visitado
            fila.add(verticeInicial); // Adiciona o vértice inicial à fila

            while (!fila.isEmpty()) { // Enquanto a fila não estiver vazia
                int vertice = fila.poll(); // Remove o vértice da frente da fila
                caminho.add(vertice); // Adiciona o vértice ao caminho
                for (int sucessor : listaAdjacencia[vertice]) { // Itera sobre os sucessores
                    if (!visitado[sucessor]) { // Se o sucessor não foi visitado
                        visitado[sucessor] = true; // Marca como visitado
                        fila.add(sucessor); // Adiciona o sucessor à fila
                    }
                }
            }
            return caminho; // Retorna o caminho encontrado
        }
    }

    // Método principal para leitura do arquivo, construção do grafo e exibição das informações
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // Caminho do arquivo. Pode ser passado via argumento ou usado o default.
        String caminhoArquivo = "graph-test-100-1.txt";
        if (args.length > 0) {
            caminhoArquivo = args[0]; // Se um argumento for passado, usa como caminho do arquivo
        }

        try {
            Scanner scanner = new Scanner(new File(caminhoArquivo)); // Cria um scanner para ler o arquivo

            // Lê a primeira linha: quantidade de vértices e de arestas.
            int qtdVertices = scanner.nextInt();
            int qtdArestas = scanner.nextInt();

            // Cria o grafo.
            Grafo grafo = new Grafo(qtdVertices);

            // Lê cada uma das arestas e adiciona ao grafo.
            for (int i = 0; i < qtdArestas; i++) {
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();
                grafo.adicionarAresta(origem, destino); // Adiciona a aresta ao grafo
            }
            scanner.close(); // Fecha o scanner do arquivo

            // Solicita ao usuário que informe um vértice.
            Scanner input = new Scanner(System.in);
            System.out.print("Informe o vértice para obter as informações: ");
            int verticeInformado = input.nextInt();

            // Verifica se o vértice informado é válido.
            if (verticeInformado < 1 || verticeInformado > qtdVertices) {
                System.out.println("Vértice informado inválido. Deve estar entre 1 e " + qtdVertices);
                return; // Sai se o vértice não for válido
            }

            // Escolha do método de busca
            System.out.print("Escolha o método de busca (1 para Busca em Profundidade, 2 para Busca em Largura): ");
            int escolha = input.nextInt();

            List<Integer> caminho = new ArrayList<>(); // Lista para armazenar o caminho encontrado
            if (escolha == 1) {
                caminho = grafo.BuscaEmProfundidade(verticeInformado); // Chama o método de DFS
                System.out.println("Caminho encontrado usando Busca em Profundidade: " + caminho);
            } else if (escolha == 2) {
                caminho = grafo.BuscaEmLargura(verticeInformado); // Chama o método de BFS
                System.out.println("Caminho encontrado usando Busca em Largura: " + caminho);
            } else {
                System.out.println("Escolha inválida."); // Mensagem de erro para escolha inválida
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

            input.close(); // Fecha o scanner de entrada
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + caminhoArquivo); // Mensagem de erro se o arquivo não for encontrado
        }
    }
}
