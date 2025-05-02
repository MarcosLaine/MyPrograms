import java.io.*;
import java.util.*;

public class OrdenacaoPorSelecaoJogador {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoPorSelecaoJogador(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ondeEstudou = ondeEstudou;
        this.anoNasc = anoNasc;
        this.estadoNasc = estadoNasc;
        this.cidadeNasc = cidadeNasc;
    }

    public static int comparacoes = 0; // Variável para contar o número de comparações
    public static int movimentacoes = 0; // Variável para contar o número de movimentações (trocas)
    private static final String FILE_NAME = "/tmp/playersAtualizado.csv"; // Caminho do arquivo de dados

    public static void main(String[] args) {
        List<OrdenacaoPorSelecaoJogador> listaJogadores = lerDadosDoArquivo(); // Lê os dados do arquivo

        List<OrdenacaoPorSelecaoJogador> listaEscolhidos = new ArrayList<>();
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break; // Encerra ao receber "FIM"
            }
            int jogadorId = Integer.parseInt(input);
            for (OrdenacaoPorSelecaoJogador jogador : listaJogadores) {
                if (jogador.id == jogadorId) {
                    listaEscolhidos.add(jogador);
                    jogador.toString(); // Adiciona a funcionalidade de impressão do jogador
                    break;
                }
            }
        }

        ordenacaoPorSelecaoJogador(listaEscolhidos); // Realiza a ordenação por seleção

        // Imprime a lista de jogadores ordenada
        for (OrdenacaoPorSelecaoJogador jogador : listaEscolhidos) {
            System.out.println(jogador);
        }
    }

    public static void ordenacaoPorSelecaoJogador(List<OrdenacaoPorSelecaoJogador> vetor) {
        int n = vetor.size();
        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++; // Incrementa o número de comparações
                if (vetor.get(j).name.compareTo(vetor.get(menorIndice).name) < 0) {
                    menorIndice = j;
                }
            }
            if (i != menorIndice) {
                Collections.swap(vetor, i, menorIndice);
                movimentacoes++; // Incrementa o número de movimentações (trocas)
            }
        }
    }

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + weight + " ## " + height + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou)  + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }

    /* A função a seguir lê e ordena os dados do arquivo .csv, separando sempre que encontra uma ',' */
    public static List<OrdenacaoPorSelecaoJogador> lerDadosDoArquivo() {
        List<OrdenacaoPorSelecaoJogador> listaJogadores = new ArrayList<>();
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

                    OrdenacaoPorSelecaoJogador jogador = new OrdenacaoPorSelecaoJogador(id, name, weight, height, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
                    listaJogadores.add(jogador);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }

    public static void criarArquivoLog(long tempoExecucao, int comparacoes, int movimentacoes) {
        String matricula = "803627";

        try (PrintWriter logFile = new PrintWriter("matricula_selecao.txt")) {
            logFile.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
