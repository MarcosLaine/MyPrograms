import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Definição da classe Jogador1
public class Jogador1 {
    private int id;
    private String name;
    private int weight;
    private int height;
    private String ondeEstudou;
    private int anoNasc;
    private String estadoNasc;
    private String cidadeNasc;

    // Lista para armazenar os objetos Jogador1
    private static List<Jogador1> listaJogadores = new ArrayList<>();

    // Nome do arquivo CSV que contém os dados dos jogadores
    private static final String FILE_NAME = "/tmp/players.csv";

    // CONSTRUTORES

    // Construtor parametrizado para inicializar os atributos de Jogador1
    public Jogador1(int id, String name, int height, int weight, String ondeEstudou, int anoNasc,
            String cidadeNasc, String estadoNasc) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.ondeEstudou = ondeEstudou;
        this.anoNasc = anoNasc;
        this.cidadeNasc = cidadeNasc;
        this.estadoNasc = estadoNasc;
    }

    // Construtor vazio
    public Jogador1() {
        while (true) {
            // Loop para ler e buscar jogadores
            System.out.println("Informe o ID do jogador (ou 'FIM' para encerrar):");
            String input = MyIO.readLine().trim();
            // Condição de parada quando recebe 'FIM'
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            int jogador1Id = Integer.parseInt(input);
            boolean jogador1Encontrado = false;

            for (Jogador1 jogador1 : listaJogadores) {
                if (jogador1.getId() == jogador1Id) {
                    jogador1Encontrado = true;
                    jogador1.imprimir();
                    break;
                }
            }
            if (!jogador1Encontrado) {
                System.out.println("Jogador1 não encontrado");
            }
        }
        System.exit(0);
    }

    // Métodos get e set para acessar e modificar os atributos de Jogador1
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getOndeEstudou() {
        return ondeEstudou;
    }

    public void setOndeEstudou(String ondeEstudou) {
        this.ondeEstudou = ondeEstudou;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getCidadeNasc() {
        return cidadeNasc;
    }

    public void setCidadeNasc(String cidadeNasc) {
        this.cidadeNasc = cidadeNasc;
    }

    public String getEstadoNasc() {
        return estadoNasc;
    }

    public void setEstadoNasc(String estadoNasc) {
        this.estadoNasc = estadoNasc;
    }

    // Método imprimir para exibir os detalhes de um jogador
    public void imprimir() {
        System.out.print("[" + id);
        System.out.print(" ## " + (name != null ? name : "não informado"));
        System.out.print(" ## " + height);
        System.out.print(" ## " + weight);
        System.out.print(" ## " + anoNasc);
        System.out.print(" ## " + (ondeEstudou != null ? ondeEstudou : "não informado"));
        System.out.print(" ## " + (cidadeNasc != null ? cidadeNasc : "não informado"));
        System.out.print(" ## " + (estadoNasc != null ? estadoNasc : "não informado"));
        System.out.println("]");
    }

    // Método estático para ler dados do arquivo CSV e preencher a lista de Jogador1
    public static void lerDadosDoArquivo() {
        listaJogadores.clear();
        // Ler dados do arquivo players.csv
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
                    String name = (atributo.length > 1) ? atributo[1].trim() : "nao informado";
                    int height = (atributo.length > 2) ? Integer.parseInt(atributo[2].trim()) : 0;
                    int weight = (atributo.length > 3) ? Integer.parseInt(atributo[3].trim()) : 0;
                    String ondeEstudou = (atributo.length > 4) ? atributo[4].trim() : "nao informado";
                    int anoNasc = (atributo.length > 5) ? Integer.parseInt(atributo[5].trim()) : 0;
                    String cidadeNasc = (atributo.length > 6) ? atributo[6].trim() : "nao informado";
                    String estadoNasc = (atributo.length > 7) ? atributo[7].trim() : "nao informado";
                    // Cria um objeto Jogador1 e adiciona na lista
                    Jogador1 jogador1 = new Jogador1(id, name, height, weight, ondeEstudou, anoNasc,
                            cidadeNasc, estadoNasc);
                    listaJogadores.add(jogador1);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    // Método main
    public static void main(String[] args) {
        // Chama o método para ler dados do arquivo CSV
        lerDadosDoArquivo();
        // Inicia o loop para criar objetos Jogador1 a partir da entrada do usuário
        while (true) {
            new Jogador1();
        }
    }
}
