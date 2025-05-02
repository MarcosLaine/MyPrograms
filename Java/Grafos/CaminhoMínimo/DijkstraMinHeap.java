import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Dijkstra usando heap mínimo implementado manualmente.
 */
public class DijkstraMinHeap {

    static class Edge {
        int to;
        double weight;
        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Grafo {
        private final int n;
        private final List<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public Grafo(int n) {
            this.n = n;
            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void adicionarAresta(int u, int v, double w) {
            adj[u].add(new Edge(v, w));
        }

        /**
         * Executa Dijkstra usando MinHeap manual para (dist, arestas).
         */
        public PathInfo dijkstra(int src, int tgt) {
            double[] dist = new double[n + 1];
            int[] edgeCnt = new int[n + 1];
            int[] parent  = new int[n + 1];

            Arrays.fill(dist, Double.POSITIVE_INFINITY);
            Arrays.fill(edgeCnt, Integer.MAX_VALUE);
            dist[src] = 0;
            edgeCnt[src] = 0;

            MinHeap heap = new MinHeap(dist, edgeCnt);
            while (!heap.isEmpty()) {
                int u = heap.extractMin();
                if (u == tgt) break;
                if (dist[u] == Double.POSITIVE_INFINITY) break;

                for (Edge e : adj[u]) {
                    int v = e.to;
                    double nd = dist[u] + e.weight;
                    int    ne = edgeCnt[u] + 1;
                    if (nd < dist[v] ||
                        (Math.abs(nd - dist[v]) < 1e-9 && ne < edgeCnt[v])) {
                        dist[v]     = nd;
                        edgeCnt[v]  = ne;
                        parent[v]   = u;
                        heap.decreaseKey(v);
                    }
                }
            }

            if (dist[tgt] == Double.POSITIVE_INFINITY) return null;
            LinkedList<Integer> path = new LinkedList<>();
            for (int v = tgt; v != 0; v = parent[v]) {
                path.addFirst(v);
                if (v == src) break;
            }
            return new PathInfo(dist[tgt], edgeCnt[tgt], path);
        }
    }

    /** Informação de resultado de caminho mínimo */
    static class PathInfo {
        final double distance;
        final int    edgeCount;
        final List<Integer> path;
        PathInfo(double d, int ec, List<Integer> p) {
            distance = d; edgeCount = ec; path = p;
        }
    }

    /**
     * Heap mínimo customizado que armazena os vértices 1..n,
     * ordenando por (dist[v], then edgeCnt[v]).
     */
    static class MinHeap {
        private int size;
        private final int[] heap;    // heap[1..size] = vértices
        private final int[] pos;     // pos[v] = índice de v em heap, ou -1 se extraído
        private final double[] dist;
        private final int[] edgeCnt;

        MinHeap(double[] dist, int[] edgeCnt) {
            this.dist    = dist;
            this.edgeCnt = edgeCnt;
            this.size    = dist.length - 1;
            heap = new int[size + 1];
            pos  = new int[size + 1];
            for (int v = 1; v <= size; v++) {
                heap[v] = v;
                pos[v]  = v;
            }
            for (int i = size / 2; i >= 1; i--) {
                heapifyDown(i);
            }
        }

        boolean isEmpty() {
            return size == 0;
        }

        /** Remove e retorna o vértice com menor (dist, edgeCnt). */
        int extractMin() {
            int root = heap[1];
            pos[root] = -1;
            int last = heap[size--];
            if (size >= 1) {
                heap[1] = last;
                pos[last] = 1;
                heapifyDown(1);
            }
            return root;
        }

        /** Após atualizar dist[v] e edgeCnt[v], bubbling-up v. */
        void decreaseKey(int v) {
            int i = pos[v];
            if (i == -1) return;
            heapifyUp(i);
        }

        private void heapifyUp(int i) {
            while (i > 1) {
                int p = i / 2;
                if (compare(heap[i], heap[p]) < 0) {
                    swap(i, p);
                    i = p;
                } else break;
            }
        }

        private void heapifyDown(int i) {
            while (true) {
                int l = 2*i, r = 2*i+1, best = i;
                if (l <= size && compare(heap[l], heap[best]) < 0) best = l;
                if (r <= size && compare(heap[r], heap[best]) < 0) best = r;
                if (best != i) {
                    swap(i, best);
                    i = best;
                } else break;
            }
        }

        private void swap(int i, int j) {
            int vi = heap[i], vj = heap[j];
            heap[i] = vj; heap[j] = vi;
            pos[vi] = j; pos[vj] = i;
        }

        /** Compara dois vértices pelo par (dist, edgeCnt). */
        private int compare(int u, int v) {
            if (dist[u] < dist[v]) return -1;
            if (dist[u] > dist[v]) return 1;
            return Integer.compare(edgeCnt[u], edgeCnt[v]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Arquivo de grafo: ");
        String file = sc.nextLine();
        try (Scanner in = new Scanner(new File(file))) {
            int n = in.nextInt(), m = in.nextInt();
            Grafo g = new Grafo(n);
            for (int i = 0; i < m; i++) {
                int u = in.nextInt(), v = in.nextInt();
                double w = in.nextDouble();
                g.adicionarAresta(u, v, w);
            }
            System.out.print("Origem: ");
            int src = sc.nextInt();
            System.out.print("Destino: ");
            int tgt = sc.nextInt();

            // Inicia o contador de tempo
            long startTime = System.nanoTime();

            PathInfo pi = g.dijkstra(src, tgt);

            // Calcula o tempo de execução
            long endTime = System.nanoTime();
            double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos

            if (pi == null) {
                System.out.printf("Não existe caminho de %d até %d%n", src, tgt);
            } else {
                System.out.printf("Distância mínima: %.2f%n", pi.distance);
                System.out.println("Número de arestas: " + pi.edgeCount);
                System.out.print("Caminho: ");
                for (int i = 0; i < pi.path.size(); i++) {
                    System.out.print(pi.path.get(i));
                    if (i + 1 < pi.path.size()) System.out.print(" -> ");
                }
                System.out.println();
            }
            System.out.printf("Tempo de execução: %.3f ms%n", executionTime);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + file);
        } finally {
            sc.close();
        }
    }
}
