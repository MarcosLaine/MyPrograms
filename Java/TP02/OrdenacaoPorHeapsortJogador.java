import java.io.*;
import java.util.*;

public class OrdenacaoPorHeapsortJogador {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    public OrdenacaoPorHeapsortJogador(int id, String name, int weight, int height, String ondeEstudou, int anoNasc, String estadoNasc, String cidadeNasc) {
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
    private static final String FILE_NAME = "/tmp/players.csv"; // Caminho do arquivo de dados

    public static void main(String[] args) {
        List<OrdenacaoPorHeapsortJogador> listaJogadores = lerDadosDoArquivo(); // Lê os dados do arquivo

        List<OrdenacaoPorHeapsortJogador> listaEscolhidos = new ArrayList<>();
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break; // Encerra ao receber "FIM"
            }
            int jogadorId = Integer.parseInt(input);
            for (OrdenacaoPorHeapsortJogador jogador : listaJogadores) {
                if (jogador.id == jogadorId) {
                    listaEscolhidos.add(jogador);
                    jogador.toString(); // Adiciona a funcionalidade de impressão do jogador
                    break;
                }
            }
        }

        long startTime = System.nanoTime();
        ordenacaoPorHeapsortJogador(listaEscolhidos); // Realiza a ordenação por Heapsort
        long endTime = System.nanoTime();

        // Imprime a lista de jogadores ordenada
        for (OrdenacaoPorHeapsortJogador jogador : listaEscolhidos) {
            System.out.println(jogador);
        }

        // Cria o arquivo de log
        criarArquivoLog("sua_matricula", comparacoes, movimentacoes, (endTime - startTime));
    }

    public static void ordenacaoPorHeapsortJogador(List<OrdenacaoPorHeapsortJogador> vetor) {
        int n = vetor.size();

        // Construir um heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // Extrair elementos do heap e rearranjar
        for (int i = n - 1; i >= 0; i--) {
            // Mova a raiz atual para o final
            Collections.swap(vetor, 0, i);

            // Chame o heapify na subárvore reduzida
            heapify(vetor, i, 0);
        }
    }

    public static void heapify(List<OrdenacaoPorHeapsortJogador> vetor, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
    
        comparacoes++; // Incrementa o número de comparações
        if (esquerda < n && (vetor.get(esquerda).height > vetor.get(maior).height || 
           (vetor.get(esquerda).height == vetor.get(maior).height && vetor.get(direita).name.compareTo(vetor.get(maior).name) < 0))) {
            maior = esquerda;
        }
    
        comparacoes++; // Incrementa o número de comparações
        if (direita < n && (vetor.get(direita).height > vetor.get(maior).height || 
            (vetor.get(direita).height == vetor.get(maior).height && vetor.get(esquerda).name.compareTo(vetor.get(maior).name) < 0))) {
            maior = direita;
        }
    
        // Se o maior não é a raiz
        if (maior != i) {
            Collections.swap(vetor, i, maior);
            movimentacoes++; // Incrementa o número de movimentações (trocas)
    
            // Recursivamente heapify a subárvore afetada
            heapify(vetor, n, maior);
        }
    }
    
    
    

    @Override
    public String toString() {
        return "[" + id + " ## " + (name.isEmpty() ? "nao informado" : name) + " ## " + height + " ## " + weight + " ## " + anoNasc + " ## " + (ondeEstudou.isEmpty() ? "nao informado" : ondeEstudou) + " ## " + (estadoNasc.isEmpty() ? "nao informado" : estadoNasc) + " ## " + (cidadeNasc.isEmpty() ? "nao informado" : cidadeNasc) + "]";
    }

    /* A função a seguir lê e ordena os dados do arquivo .csv, separando sempre que encontra uma ',' */
    public static List<OrdenacaoPorHeapsortJogador> lerDadosDoArquivo() {
        List<OrdenacaoPorHeapsortJogador> listaJogadores = new ArrayList<>();
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

                    OrdenacaoPorHeapsortJogador jogador = new OrdenacaoPorHeapsortJogador(id, name, height, weight, ondeEstudou, anoNasc, estadoNasc, cidadeNasc);
                    listaJogadores.add(jogador);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }

    public static void criarArquivoLog(String matricula, int comparacoes, int movimentacoes, long tempoExecucao) {
        try (PrintWriter logFile = new PrintWriter(matricula + "_heapsort.txt")) {
            logFile.println(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
