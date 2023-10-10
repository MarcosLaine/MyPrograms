import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PesquisaSequencialJogador {
    private int id;
    private String nome;
    private static List<PesquisaSequencialJogador> listaJogadores = new ArrayList<>();
    private static final String FILE_NAME = "/tmp/players.csv"; // Para acessar o arquivo com os jogadores
    public static int comparacoes = 0;
    public static int movimentacoes = 0;

    // CONSTRUTOR
    public PesquisaSequencialJogador(int id, String nome, int altura, int peso, String universidade,
                           int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        // Atribua os outros atributos aqui
    }

    public static void main(String[] args) throws IOException {
        listaJogadores = lerDadosDoArquivo(); // chama o arquivo para leitura
        long startTime = System.currentTimeMillis(); // inicia a contagem de tempo
        List<PesquisaSequencialJogador> listaEscolhidos = new ArrayList<>();
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {// encerra ao receber fim
                break;
            }
            int jogadorId = Integer.parseInt(input);
            for (PesquisaSequencialJogador jogador : listaJogadores) {
                if (jogador.getId() == jogadorId) {
                    listaEscolhidos.add(jogador); // adiciona o jogador escolhido na lista
                    break;
                }
            }
        }
        while (true) {
            String nomeJogador = MyIO.readLine().trim();
            if (nomeJogador.equalsIgnoreCase("FIM")) {
                break;
            }
            verificaJ(nomeJogador, listaEscolhidos);
        }
        long endTime = System.currentTimeMillis(); // Fim da contagem de tempo
        long tempoExecucao = endTime - startTime;

        // Crie o arquivo de log
        criarArquivoLog(tempoExecucao);
    }

    public static void verificaJ(String nome, List<PesquisaSequencialJogador> listaIdsEscolhidos) {
        boolean encontrado = false;
        for (PesquisaSequencialJogador jogador : listaIdsEscolhidos) {
            comparacoes++; // Incrementa o número de comparações
            if (jogador.getNome().equalsIgnoreCase(nome)) {
                System.out.println("SIM");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("NAO");
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<PesquisaSequencialJogador> lerDadosDoArquivo() {
        List<PesquisaSequencialJogador> listaJogadores = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) {
                String[] atributos = ln.split(",");
                if (atributos.length >= 1) {
                    int id;
                    try {
                        id = Integer.parseInt(atributos[0].trim());
                    } catch (NumberFormatException a) {
                        continue;
                    }
                    String nome = (atributos.length > 1) ? atributos[1].trim() : "";
                    int altura = (atributos.length > 2) ? Integer.parseInt(atributos[2].trim()) : 0;
                    int peso = (atributos.length > 3) ? Integer.parseInt(atributos[3].trim()) : 0;
                    String universidade = (atributos.length > 4) ? atributos[4].trim() : "";
                    int anoNascimento = (atributos.length > 5) ? Integer.parseInt(atributos[5].trim()) : 0;
                    String cidadeNascimento = (atributos.length > 6) ? atributos[6].trim() : "";
                    String estadoNascimento = (atributos.length > 7) ? atributos[7].trim() : "";
                    PesquisaSequencialJogador jogador = new PesquisaSequencialJogador(id, nome, altura, peso, universidade,
                            anoNascimento, cidadeNascimento, estadoNascimento);
                    listaJogadores.add(jogador); // adiciona o jogador a lista de jogadores
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return listaJogadores;
    }

    // Método para criar o arquivo de log
    public static void criarArquivoLog(long tempoExecucao) throws IOException {
        String matricula = "803627";

        // Crie o arquivo de log
        try (FileWriter logFile = new FileWriter("matricula_sequencial.txt")) {
            logFile.write(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
