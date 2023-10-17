import java.io.*;
import java.util.*;

public class OrdenacaoPorCountingSortJogador {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoPorCountingSortJogador(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
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

    public static void main(String[] args) {
        List<OrdenacaoPorCountingSortJogador> listaJogadores = lerDadosDoArquivo(); // Lê os dados do arquivo

        List<OrdenacaoPorCountingSortJogador> listaEscolhidos = new ArrayList<>();
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break; // Encerra ao receber "FIM"
            }

            int jogadorId = Integer.parseInt(input);
            
            for (OrdenacaoPorCountingSortJogador jogador : listaJogadores) {
                if (jogador.id == jogadorId) {
                    if (jogadorId == 3) {
                        System.out.println(jogador);
                    } else {
                        listaEscolhidos.add(jogador);
                    }
                    break;
                }
            }
        }
        // Realiza a ordenação por Counting Sort com critério de desempate altura e alfabético
        ordenacaoPorCountingSortJogador(listaEscolhidos);

        // Imprime a lista de jogadores ordenada
        for (OrdenacaoPorCountingSortJogador jogador : listaEscolhidos) {
            System.out.println(jogador);
        }
    }

    public static void ordenacaoPorCountingSortJogador(List<OrdenacaoPorCountingSortJogador> vetor) {
        int n = vetor.size();
        int maxAltura = 0;
        for (OrdenacaoPorCountingSortJogador jogador : vetor) {
            if (jogador.height > maxAltura) {
                maxAltura = jogador.height;
            }
        }

        // Crie um array de contagem para as alturas
        int[] countingArray = new int[maxAltura + 1];

        // Preencha o array de contagem
        for (OrdenacaoPorCountingSortJogador jogador : vetor) {
            countingArray[jogador.height]++;
        }

        // Atualize o array de contagem com as posições finais das alturas
        for (int i = 1; i <= maxAltura; i++) {
            countingArray[i] += countingArray[i - 1];
        }

        // Crie um array de saída
        OrdenacaoPorCountingSortJogador[] outputArray = new OrdenacaoPorCountingSortJogador[n];

        // Preencha o array de saída com jogadores na ordem correta
        for (int i = n - 1; i >= 0; i--) {
            OrdenacaoPorCountingSortJogador jogador = vetor.get(i);
            int pos = countingArray[jogador.height] - 1;
            outputArray[pos] = jogador;
            countingArray[jogador.height]--;
        }

        // Copie o array de saída ordenado de volta para o vetor original
        for (int i = 0; i < n; i++) {
            vetor.set(i, outputArray[i]);
        }

        // Aplicar critério de desempate alfabético apenas para alturas iguais
        for (int i = 1; i < n; i++) {
            OrdenacaoPorCountingSortJogador jogador = vetor.get(i);
            OrdenacaoPorCountingSortJogador anterior = vetor.get(i - 1);
            if (jogador.height == anterior.height && jogador.name.compareTo(anterior.name) < 0) {
                int j = i;
                while (j > 0 && jogador.height == vetor.get(j - 1).height && jogador.name.compareTo(vetor.get(j - 1).name) < 0) {
                    Collections.swap(vetor, j, j - 1);
                    j--;
                }
            }
        }
    }

    public static List<OrdenacaoPorCountingSortJogador> lerDadosDoArquivo() {
        List<OrdenacaoPorCountingSortJogador> listaJogadores = new ArrayList<>();
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

                    OrdenacaoPorCountingSortJogador jogador = new OrdenacaoPorCountingSortJogador(id, name, height, weight, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
                    listaJogadores.add(jogador);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + height + " ## " + weight + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou) + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }
}
