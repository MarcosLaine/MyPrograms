import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BasicOfGraphSearch {

    static class Grafo {
        private int qtdVertices;
        private List<Integer>[] adj;
        private int[] descoberta;  // tempo de descoberta
        private int[] termino;     // tempo de término
        private int tempo;         // tempo global
        private int[] pai;
        private Set<String> arestasArvore; // Nova estrutura para rastrear arestas de árvore

        @SuppressWarnings("unchecked")
        public Grafo(int qtdVertices) {
            this.qtdVertices = qtdVertices;
            adj = new ArrayList[qtdVertices + 1];
            descoberta = new int[qtdVertices + 1];
            termino = new int[qtdVertices + 1];
            pai = new int[qtdVertices + 1];
            arestasArvore = new HashSet<>(); // Inicializa o conjunto
            for (int i = 1; i <= qtdVertices; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void adicionarAresta(int origem, int destino) {
            adj[origem].add(destino);
        }

        public void DFS(int verticeInicial) {
            boolean[] visitado = new boolean[qtdVertices + 1];
            tempo = 0;
            arestasArvore.clear(); // Limpa o conjunto antes de começar
            
            // Primeiro fazemos DFS completa começando do vértice inicial
            DFSUtil(verticeInicial, visitado);
            
            // Se houver vértices não visitados, continuamos a DFS a partir deles
            for (int i = 1; i <= qtdVertices; i++) {
                if (!visitado[i]) {
                    DFSUtil(i, visitado);
                }
            }

            System.out.println("Arestas de Árvore encontradas:");
            for (String aresta : arestasArvore) {
                System.out.println(aresta);
            }

            System.out.println("\nClassificação das arestas divergentes do vértice " + verticeInicial + ":");
            for (int sucessor : adj[verticeInicial]) {
                String tipo = classificarAresta(verticeInicial, sucessor);
                System.out.println(verticeInicial + " -> " + sucessor + ": " + tipo);
            }
        }

        private String classificarAresta(int u, int v) {
            String aresta = u + " -> " + v;
            // Primeiro verifica se é aresta de árvore
            if (arestasArvore.contains(aresta)) {
                return "Árvore";
            }
            // Se v foi descoberto antes de u
            else if (descoberta[v] < descoberta[u]) {
                return "Retorno";
            }
            // Se v foi descoberto depois de u mas antes de u terminar
            else if (descoberta[v] > descoberta[u] && termino[v] < termino[u]) {
                return "Avanço";
            }
            // Se v foi descoberto depois que u terminou
            else {
                return "Cruzamento";
            }
        }

        private void DFSUtil(int v, boolean[] visitado) {
            visitado[v] = true;
            descoberta[v] = ++tempo;
            Collections.sort(adj[v]);

            for (int n : adj[v]) {
                if (!visitado[n]) {
                    arestasArvore.add(v + " -> " + n); // Adiciona a aresta de árvore
                    DFSUtil(n, visitado);
                }
            }
            
            termino[v] = ++tempo;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe o nome do arquivo: ");
        String arquivo = sc.nextLine();

        try {
            Scanner leitor = new Scanner(new File(arquivo));
            int qtdVertices = leitor.nextInt();
            int qtdArestas = leitor.nextInt();

            Grafo g = new Grafo(qtdVertices);

            for (int i = 0; i < qtdArestas; i++) {
                int origem = leitor.nextInt();
                int destino = leitor.nextInt();
                g.adicionarAresta(origem, destino);
            }

            leitor.close();

            System.out.print("Informe o vértice inicial: ");
            int verticeInicial = sc.nextInt();

            g.DFS(verticeInicial);

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + arquivo);
        }

        sc.close();
    }
}