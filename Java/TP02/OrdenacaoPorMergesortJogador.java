import java.io.*;
import java.util.*;

public class OrdenacaoPorMergesortJogador {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoPorMergesortJogador(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ondeEstudou = ondeEstudou;
        this.anoNasc = anoNasc;
        this.estadoNasc = estadoNasc;
        this.cidadeNasc = cidadeNasc;
    }

    public static final String FILE_NAME = "/tmp/playersAtualizado.csv"; // Substitua pelo caminho correto do arquivo

    public static void main(String[] args) {
        List<OrdenacaoPorMergesortJogador> listaJogadores = lerDadosDoArquivo();

        List<OrdenacaoPorMergesortJogador> listaEscolhidos = new ArrayList<>();
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            int jogadorId = Integer.parseInt(input);
            for (OrdenacaoPorMergesortJogador jogador : listaJogadores) {
                if (jogador.id == jogadorId) {
                    listaEscolhidos.add(jogador);
                    jogador.toString();
                    break;
                }
            }
        }

        ordenacaoPorMergesortJogador(listaEscolhidos);

        for (OrdenacaoPorMergesortJogador jogador : listaEscolhidos) {
            System.out.println(jogador);
        }
    }

    public static void ordenacaoPorMergesortJogador(List<OrdenacaoPorMergesortJogador> vetor) {
        int n = vetor.size();
        if (n <= 1) {
            return;
        }

        int meio = n / 2;
        List<OrdenacaoPorMergesortJogador> esquerda = new ArrayList<>(vetor.subList(0, meio));
        List<OrdenacaoPorMergesortJogador> direita = new ArrayList<>(vetor.subList(meio, n));

        ordenacaoPorMergesortJogador(esquerda);
        ordenacaoPorMergesortJogador(direita);

        int i = 0, j = 0, k = 0;
        while (i < esquerda.size() && j < direita.size()) {
            String chaveEsquerda = esquerda.get(i).ondeEstudou.isEmpty() ? esquerda.get(i).name : esquerda.get(i).ondeEstudou;
            String chaveDireita = direita.get(j).ondeEstudou.isEmpty() ? direita.get(j).name : direita.get(j).ondeEstudou;

            // Adicione um critério de desempate pela ordem alfabética dos jogadores empatados
            if (chaveEsquerda.equals(chaveDireita)) {
                int comparacaoNomes = esquerda.get(i).name.compareTo(direita.get(j).name);
                if (comparacaoNomes < 0) {
                    vetor.set(k, esquerda.get(i));
                    i++;
                } else {
                    vetor.set(k, direita.get(j));
                    j++;
                }
            } else {
                if (chaveEsquerda.compareTo(chaveDireita) < 0) {
                    vetor.set(k, esquerda.get(i));
                    i++;
                } else {
                    vetor.set(k, direita.get(j));
                    j++;
                }
            }

            k++;
        }

        while (i < esquerda.size()) {
            vetor.set(k, esquerda.get(i));
            i++;
            k++;
        }

        while (j < direita.size()) {
            vetor.set(k, direita.get(j));
            j++;
            k++;
        }
    }

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + weight + " ## " + height + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou) + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }

    public static List<OrdenacaoPorMergesortJogador> lerDadosDoArquivo() {
        List<OrdenacaoPorMergesortJogador> listaJogadores = new ArrayList<>();
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

                    OrdenacaoPorMergesortJogador jogador = new OrdenacaoPorMergesortJogador(id, name, weight, height, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
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
