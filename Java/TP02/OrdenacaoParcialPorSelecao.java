import java.io.*;
import java.util.*;

public class OrdenacaoParcialPorSelecao {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoParcialPorSelecao(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
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


    public static void ordenacaoParcialPorSelecao(List<OrdenacaoParcialPorSelecao> vetor, int k) {
        int n = vetor.size();
        for (int i = 0; i < k; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor.get(j).name.compareTo(vetor.get(menorIndice).name) < 0) {
                    menorIndice = j;
                }
            }
            if (i != menorIndice) {
                Collections.swap(vetor, i, menorIndice);
            }
        }
    }

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + weight + " ## " + height + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou)  + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }

    public static void main(String[] args) {
        List<OrdenacaoParcialPorSelecao> listaJogadores = lerDadosDoArquivo(); // Lê os dados do arquivo

        List<OrdenacaoParcialPorSelecao> listaEscolhidos = new ArrayList<>();
        int k = 10; // Substitua 'k' pelo número desejado de elementos a serem ordenados parcialmente
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break; // Encerra ao receber "FIM"
            }
            int jogadorId = Integer.parseInt(input);
            for (OrdenacaoParcialPorSelecao jogador : listaJogadores) {
                if (jogador.id == jogadorId) {
                    listaEscolhidos.add(jogador);
                    jogador.toString(); // Adiciona a funcionalidade de impressão do jogador
                    break;
                }
            }
        }

        OrdenacaoParcialPorSelecao.ordenacaoParcialPorSelecao(listaEscolhidos, k); // Realiza a ordenação parcial

        // Imprime a lista de jogadores ordenada parcialmente
        for (int i = 0; i < k; i++) {
            System.out.println(listaEscolhidos.get(i));
        }
    }

    public static void criarArquivoLog(long tempoExecucao, int comparacoes, int movimentacoes) {
        String matricula = "803627";

        try (PrintWriter logFile = new PrintWriter("matricula_selecao.txt")) {
            logFile.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* A função a seguir lê e ordena os dados do arquivo .csv, separando sempre que encontra uma ',' */
    public static List<OrdenacaoParcialPorSelecao> lerDadosDoArquivo() {
        List<OrdenacaoParcialPorSelecao> listaJogadores = new ArrayList<>();
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

                    OrdenacaoParcialPorSelecao jogador = new OrdenacaoParcialPorSelecao(id, name, weight, height, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
                    listaJogadores.add(jogador);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }
}
