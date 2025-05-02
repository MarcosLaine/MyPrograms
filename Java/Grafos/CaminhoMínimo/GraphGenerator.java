import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Gera 8 grafos dirigidos e ponderados:
 * - Para cada n em {100, 1000, 10000, 100000}
 *     • random_n.txt : grafo Erdős–Rényi com p = 5/n, m ≈ 5·(n−1) arestas
 *     • grid_n.txt   : grafo grade 2D (arestas para direita e para baixo)
 * Cada arquivo termina com um par “1 n” (origem e destino).
 */
public class GraphGenerator {
    private static final Random rnd = new Random();
    private static final int[] SIZES = {100, 1000, 10000, 100000};
    private static final int W_MIN = 1;
    private static final int W_MAX = 100;

    public static void main(String[] args) throws IOException {
        for (int n : SIZES) {
            generateRandom(n);
            generateGrid(n);
        }
        System.out.println("Geração concluída!");
    }

    /** Gera grafo aleatório com m = 5·(n−1) arestas, prob. p=5/n */
    private static void generateRandom(int n) throws IOException {
        int m = 5 * (n - 1);
        Set<Long> edgeSet = new HashSet<>(m);
        // empacota cada aresta (u,v) em um long único
        while (edgeSet.size() < m) {
            int u = rnd.nextInt(n) + 1;
            int v = rnd.nextInt(n) + 1;
            if (u == v) continue;
            long key = ((long) u << 32) | (v & 0xffffffffL);
            edgeSet.add(key);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("random_" + n + "_weighted.txt"))) {
            bw.write(n + " " + m + "\n");
            for (long key : edgeSet) {
                int u = (int) (key >>> 32);
                int v = (int) key;
                int w = rnd.nextInt(W_MAX - W_MIN + 1) + W_MIN;
                bw.write(u + " " + v + " " + w + "\n");
            }
            bw.write("1 " + n + "\n");
        }
    }

    /** Gera grafo grade 2D com n vértices e arestas apenas para direita e para baixo */
    private static void generateGrid(int n) throws IOException {
        int rows = (int) Math.floor(Math.sqrt(n));
        int cols = (int) Math.ceil((double) n / rows);
        List<String> edges = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int id = i * cols + j + 1;
                if (id > n) continue;
                // vizinho à direita
                if (j + 1 < cols) {
                    int idR = i * cols + (j + 1) + 1;
                    if (idR <= n) {
                        int w = rnd.nextInt(W_MAX - W_MIN + 1) + W_MIN;
                        edges.add(id + " " + idR + " " + w);
                    }
                }
                // vizinho abaixo
                if (i + 1 < rows) {
                    int idD = (i + 1) * cols + j + 1;
                    if (idD <= n) {
                        int w = rnd.nextInt(W_MAX - W_MIN + 1) + W_MIN;
                        edges.add(id + " " + idD + " " + w);
                    }
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("grid_" + n + "_weighted.txt"))) {
            bw.write(n + " " + edges.size() + "\n");
            for (String e : edges) {
                bw.write(e + "\n");
            }
            bw.write("1 " + n + "\n");
        }
    }
}
