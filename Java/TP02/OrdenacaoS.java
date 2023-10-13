import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdenacaoS {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    private static List<OrdenacaoS> listaJogadores = new ArrayList<>();
    private static final String FILE_NAME = "/tmp/players.csv"; // para acessar o arquivo com os jogadores
    // numeros de comparações e movimentações para o log
    public static int comparacoes = 0;
    public static int movimentacoes = 0;

    // CONSTRUTORES
    public OrdenacaoS(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public OrdenacaoS() {
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {// encerra ao receber fim
                break;
            }
            int jogadorId = Integer.parseInt(input);
            boolean jogadorEncontrado = false;
            for (OrdenacaoS jogador : listaJogadores) {
                if (jogador.getId() == jogadorId) {
                    jogadorEncontrado = true;
                    jogador.imprimir();
                    break;
                }
            }
            if (!jogadorEncontrado) {
                /* System.out.println("Jogador não encontrado."); */}
        }
        System.exit(0);// para sair do loop
    }// Métodos get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // Método para ler os dados do arquivo CSV e criar objetos
    public static void lerDadosDoArquivo() {
        listaJogadores.clear();
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) {
                String[] atributo = ln.split(",");
                if (atributo.length >= 1) {
                    int id;
                    try {
                        id = Integer.parseInt(atributo[0].trim());
                    } catch (NumberFormatException a) {
                        continue;
                    }
                    String nome = (atributo.length > 1) ? atributo[1].trim() : "nao informado";
                    int altura = (atributo.length > 2) ? Integer.parseInt(atributo[2].trim()) : 0;
                    int peso = (atributo.length > 3) ? Integer.parseInt(atributo[3].trim()) : 0;
                    String universidade = (atributo.length > 4) ? atributo[4].trim() : "nao informado";
                    int anoNascimento = (atributo.length > 5) ? Integer.parseInt(atributo[5].trim()) : 0;
                    String cidadeNascimento = (atributo.length > 6) ? atributo[6].trim() : "nao informado";
                    String estadoNascimento = (atributo.length > 7) ? atributo[7].trim() : "nao informado";
                    OrdenacaoS jogador = new OrdenacaoS(id, nome, altura, peso, universidade, anoNascimento,
                            cidadeNascimento, estadoNascimento);
                    listaJogadores.add(jogador);// adiciona os escolhidos a lista jogadores
                }
            }
        } catch (IOException x) {// em caso de excessão
            x.printStackTrace();
        }
    }

    // Ordenar a lista de jogadores por nome usando o algoritmo de seleção
    public static void ordenaJogador(List<OrdenacaoS> jogadores) {
        for (int i = 0; i < jogadores.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < jogadores.size(); j++) {
                if (jogadores.get(j).getNome().compareTo(jogadores.get(minIndex).getNome()) < 0) {
                    minIndex = j;
                    movimentacoes++; // Incrementar o número de movimentações
                }
            }
            // Trocar o jogador atual pelo jogador com o menor nome
            OrdenacaoS temp = jogadores.get(i);
            jogadores.set(i, jogadores.get(minIndex));
            jogadores.set(minIndex, temp);
        }
    }

    // Método para imprimir um jogador
    public void imprimir() {
        System.out.print("[" + id);
        System.out.print(" ## " + (nome != null ? nome : "nao informado"));
        System.out.print(" ## " + altura);
        System.out.print(" ## " + peso);
        System.out.print(" ## " + anoNascimento);
        System.out.print(" ## " + (universidade != null ? universidade : "nao informado"));
        System.out.print(" ## " + (cidadeNascimento != null ? cidadeNascimento : "nao informado"));
        System.out.print(" ## " + (estadoNascimento != null ? estadoNascimento : "nao informado"));
        System.out.println("]");
    }

    public static void main(String[] args) {
        lerDadosDoArquivo();
        List<OrdenacaoS> jogadoresSelecionados = new ArrayList<>();
        long startTime = System.currentTimeMillis(); // Para calcular o tempo da ordenação (início)
        while (true) {
            // System.out.println("Digite o ID do jogador (ou 'fim' para encerrar):");
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("fim")) {
                break;
            }
            int jogadorId;
            try {
                jogadorId = Integer.parseInt(input);
            } catch (NumberFormatException e) {// System.out.println("ID inválido. Tente novamente.");
                continue;
            }
            boolean jogadorEncontrado = false;
            for (OrdenacaoS jogador : listaJogadores) {
                comparacoes++; // Incrementar o número de comparações
                if (jogador.getId() == jogadorId) {
                    jogadoresSelecionados.add(jogador);
                    jogadorEncontrado = true;
                    break;
                }
            }
            if (!jogadorEncontrado) {
                /* System.out.println("Jogador não encontrado."); */}
        }
        ordenaJogador(jogadoresSelecionados);
        // Calcular o tempo de execução
        long endTime = System.currentTimeMillis();
        long tempoExecucao = endTime - startTime;
        // Exibição do vetor ordenado
        for (OrdenacaoS jogador : jogadoresSelecionados) {
            jogador.imprimir();
        }
    }
}
