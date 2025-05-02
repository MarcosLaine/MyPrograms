import java.io.*;
import java.util.*;

public class OrdenacaoParcialQuicksort {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoParcialQuicksort(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ondeEstudou = ondeEstudou;
        this.anoNasc = anoNasc;
        this.estadoNasc = estadoNasc;
        this.cidadeNasc = cidadeNasc;
    }

    private static final String FILE_NAME = "/tmp/playersAtualizado.csv"; // Caminho do arquivo de dados

    public static void ordenacaoParcialQuicksort(List<OrdenacaoParcialQuicksort> vetor, int k) {
        quicksort(vetor, 0, vetor.size() - 1, k);
    }

    private static void quicksort(List<OrdenacaoParcialQuicksort> vetor, int esquerda, int direita, int k) {
        if (esquerda < direita) {
            int indicePivo = particionar(vetor, esquerda, direita);
            if (indicePivo < k) {
                quicksort(vetor, esquerda, indicePivo, k);
                quicksort(vetor, indicePivo + 1, direita, k);
            }
        }
    }

    private static int particionar(List<OrdenacaoParcialQuicksort> vetor, int esquerda, int direita) {
        String pivo = vetor.get(esquerda).estadoNasc;
        int i = esquerda - 1;
        int j = direita + 1;
        while (true) {
            do {
                i++;
            } while (vetor.get(i).estadoNasc.compareTo(pivo) < 0);

            do {
                j--;
            } while (vetor.get(j).estadoNasc.compareTo(pivo) > 0);

            if (i >= j) {
                return j;
            }
            Collections.swap(vetor, i, j);
        }
    }

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + weight + " ## " + height + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou) + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }

    public static void main(String[] args) {
        List<OrdenacaoParcialQuicksort> listaJogadores = lerDadosDoArquivo(); // Lê os dados do arquivo

        Collections.sort(listaJogadores, new Comparator<OrdenacaoParcialQuicksort>() {
            public int compare(OrdenacaoParcialQuicksort jogador1, OrdenacaoParcialQuicksort jogador2) {
                return jogador1.estadoNasc.compareTo(jogador2.estadoNasc);
            }
        });

        int k = 10; // Substitua 'k' pelo número desejado de elementos a serem ordenados parcialmente
        List<OrdenacaoParcialQuicksort> listaEscolhidos = listaJogadores.subList(0, Math.min(k, listaJogadores.size()));

        // Imprime a lista de jogadores ordenada parcialmente
        for (OrdenacaoParcialQuicksort jogador : listaEscolhidos) {
            System.out.println(jogador);
        }
    }

    public static void criarArquivoLog(long tempoExecucao, int comparacoes, int movimentacoes) {
        String matricula = "803627";

        try (PrintWriter logFile = new PrintWriter("matricula_quicksort.txt")) {
            logFile.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* A função a seguir lê e ordena os dados do arquivo .csv, separando sempre que encontra uma ',' */
    public static List<OrdenacaoParcialQuicksort> lerDadosDoArquivo() {
        List<OrdenacaoParcialQuicksort> listaJogadores = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) {
                String[] atributos = ln.split(",");
                if (atributos.length >= 8) {
                    int id;
                    try {
                        id = Integer.parseInt(atributos[0].trim());
                    } catch (NumberFormatException a) {
                        continue;
                    }
                    String name = atributos[1].trim();
                    int weight = Integer.parseInt(atributos[2].trim());
                    int height = Integer.parseInt(atributos[3].trim());
                    String ondeEstudou = atributos[4].trim();
                    int anoNasc = Integer.parseInt(atributos[5].trim());
                    String estadoNasc = atributos[6].trim();
                    String cidadeNasc = atributos[7].trim();

                    OrdenacaoParcialQuicksort jogador = new OrdenacaoParcialQuicksort(id, name, weight, height, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
                    listaJogadores.add(jogador);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }
}
