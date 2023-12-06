

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


class Player {
    // Atributos da classe Player
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int anoNasc;
    private String cidadeNasc;
    private String estadoNasc;

    // Construtor padrão
    public Player() {
        this.id = -1;
        this.name = "";
        this.height = -1;
        this.weight = -1;
        this.university = "";
        this.anoNasc = -1;
        this.cidadeNasc = "";
        this.estadoNasc = "";
    }

    // Construtor parametrizado
    public Player(int id, String name, int height, int weight, String university, int anoNasc, String cidadeNasc, String estadoNasc) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.anoNasc = anoNasc;
        this.cidadeNasc = cidadeNasc;
        this.estadoNasc = estadoNasc;
    }

    // Métodos get e set para cada atributo
    public int getHeight() {
        return height;
    }
    public int getAnoNasc() {
        return anoNasc;
    }
    public String getCidadeNasc() {
        return cidadeNasc;
    }
    public String getEstadoNasc() {
        return estadoNasc;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public String getUniversity() {
        return university;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }
    public void setCidadeNasc(String cidadeNasc) {
        this.cidadeNasc = cidadeNasc;
    }
    public void setEstadoNasc(String estadoNasc) {
        this.estadoNasc = estadoNasc;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    // Método para preencher atributos do player
    public void preencherAtributos(String linhaDados) {
        String dadosTratados = tratarLinhaDados(linhaDados);
        String[] atributos = dadosTratados.split(",");
        
        this.setId(!atributos[0].equals("nao informado") ? Integer.parseInt(atributos[0]) : -1);
        this.setName(atributos[1]);
        this.setHeight(!atributos[2].equals("nao informado") ? Integer.parseInt(atributos[2]) : -1);
        this.setWeight(!atributos[3].equals("nao informado") ? Integer.parseInt(atributos[3]) : -1);
        this.setUniversity(atributos[4]);
        this.setAnoNasc(!atributos[5].equals("nao informado") ? Integer.parseInt(atributos[5]) : -1);
        this.setCidadeNasc(atributos[6]);
        this.setEstadoNasc(atributos.length > 7 ? atributos[7] : "");
    }

    // Método para tratar a linha de dados
    private String tratarLinhaDados(String linhaDados) {
        StringBuilder novaLinha = new StringBuilder();
        for (int i = 0; i < linhaDados.length(); i++) {
            char caracterAtual = linhaDados.charAt(i);
            char proximoCaracter = (i + 1 < linhaDados.length()) ? linhaDados.charAt(i + 1) : ' ';
            
            if (caracterAtual == ',' && proximoCaracter == ',') {
                novaLinha.append(",nao informado");
            } else {
                if (i == linhaDados.length() - 1 && caracterAtual == ',') {
                    novaLinha.append(",nao informado");
                } else {
                    novaLinha.append(caracterAtual);
                }
            }
        }
        return novaLinha.toString();
    }

    // Método para exibir informações do player
    public void exibirInformacoes() {
        System.out.println(
            id + " ## " +
            name + " ## " +
            height + " ## " +
            weight + " ## " +
            anoNasc + " ## " +
            university + " ## " +
            cidadeNasc + " ## " +
            estadoNasc + " ##"
        );
    }
}

public class Rehash {
    private final int tamanhoTabela = 25;
    private Player[] tabelaHash = new Player[tamanhoTabela];

    public Rehash() {
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < tamanhoTabela; i++) {
            tabelaHash[i] = null;
        }
    }

    private int calcularPosicaoHash(Player player) {
        return player.getHeight() % tamanhoTabela;
    }

    private int calcularRehash(Player player) {
        return (player.getHeight() + 1) % tamanhoTabela;
    }

    public void inserirPlayer(Player player) {
        int posicao = calcularPosicaoHash(player);
        if (tabelaHash[posicao] == null) {
            tabelaHash[posicao] = player;
        } else {
            posicao = calcularRehash(player);
            if (tabelaHash[posicao] == null) {
                tabelaHash[posicao] = player;
            }
        }
    }

    public void exibirTabelaHash() {
        System.out.println("Tabela Hash:");
        for (Player player : tabelaHash) {
            if (player != null) {
                player.exibirInformacoes();
            }
        }
    }

    public boolean pesquisarPlayer(String name) {
        for (Player player : tabelaHash) {
            if (player != null && player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String[] lerArquivo(String caminho) {
        File arquivo = new File(caminho);
        ArrayList<String> linhasDados = new ArrayList<>();
        try {
            Scanner leitor = new Scanner(arquivo);
            leitor.nextLine(); // Ignorar a primeira linha
            while (leitor.hasNextLine()) {
                linhasDados.add(leitor.nextLine());
            }
            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return linhasDados.toArray(new String[0]);
    }

    public static void main(String[] args) throws Exception {
        String[] linhasDados = lerArquivo("/tmp/players.csv");
        Player[] players = new Player[linhasDados.length];
        Scanner entrada = new Scanner(System.in);
        Rehash tabelaHash = new Rehash();

        for (int i = 0; i < linhasDados.length; i++) {
            Player player = new Player();
            player.preencherAtributos(linhasDados[i]);
            players[i] = player;
        }

        while (!entrada.hasNext("FIM")) {
            int idPlayer = entrada.nextInt();
            if(idPlayer < players.length){
                tabelaHash.inserirPlayer(players[idPlayer]);
            }
        }

        entrada.nextLine(); // Consumir a linha "FIM"
        entrada.nextLine(); // Consumir a linha vazia

        while (!entrada.hasNext("FIM")) {
            String namePlayer = entrada.nextLine();
            System.out.print(namePlayer + " ");
            if (tabelaHash.pesquisarPlayer(namePlayer)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        entrada.close();
    }
}
