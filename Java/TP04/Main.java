import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class ArvoreSecundaria {
    class No {
        Jogador jogador;
        No esquerda, direita;

        No(Jogador jogador) {
            this.jogador = jogador;
            esquerda = direita = null;
        }
    }

    No raiz;

    public ArvoreSecundaria() {
        raiz = null;
    }

    public void inserir(Jogador jogador) {
        raiz = inserir(raiz, jogador);
    }

    private No inserir(No no, Jogador jogador) {
        if (no == null) {
            return new No(jogador);
        }
        if (jogador.getNome().compareTo(no.jogador.getNome()) < 0) {
            no.esquerda = inserir(no.esquerda, jogador);
        } else if (jogador.getNome().compareTo(no.jogador.getNome()) > 0) {
            no.direita = inserir(no.direita, jogador);
        }
        return no;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(raiz, nome, "");
    }

    boolean pesquisar(No no, String nome, String caminho) {
        if (no == null) {
            return false;
        }
        if (nome.equals(no.jogador.getNome())) {
            System.out.print(caminho + " SIM");
            return true;
        } else if (nome.compareTo(no.jogador.getNome()) < 0) {
            return pesquisar(no.esquerda, nome, caminho + " esq");
        } else {
            return pesquisar(no.direita, nome, caminho + " dir");
        }
    }
}

class ArvoreBinaria {
    class No {
        int alturaMod15;
        No esquerda, direita;
        ArvoreSecundaria secundaria;

        No(int alturaMod15) {
            this.alturaMod15 = alturaMod15;
            esquerda = direita = null;
            secundaria = new ArvoreSecundaria();
        }
    }

    No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(Jogador jogador) {
        int chave = jogador.getAltura() % 15;
        raiz = inserir(raiz, chave, jogador);
    }

    private No inserir(No no, int chave, Jogador jogador) {
        if (no == null) {
            return new No(chave);
        }
        if (chave < no.alturaMod15) {
            no.esquerda = inserir(no.esquerda, chave, jogador);
        } else if (chave > no.alturaMod15) {
            no.direita = inserir(no.direita, chave, jogador);
        } else {
            no.secundaria.inserir(jogador);
        }
        return no;
    }

    public boolean pesquisar(String nome) {
        return pesquisar(raiz, nome, "raiz");
    }

    private boolean pesquisar(No no, String nome, String caminho) {
        if (no == null) {
            System.out.println(caminho + " NAO");
            return false;
        }

        System.out.print(caminho + " ");
        if (no.secundaria.pesquisar(null, nome, "")) {
            return true;
        }

        if (pesquisar(no.esquerda, nome, "esq")) {
            return true;
        }
        return pesquisar(no.direita, nome, "dir");
    }

}

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Jogador() {
    }

    Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // clone
    public Jogador clone(Jogador J) {
        Jogador clonado = new Jogador();
        clonado.id = J.id;
        clonado.nome = J.nome;
        clonado.altura = J.altura;
        clonado.peso = J.peso;
        clonado.universidade = J.universidade;
        clonado.anoNascimento = J.anoNascimento;
        clonado.cidadeNascimento = J.cidadeNascimento;
        clonado.estadoNascimento = J.estadoNascimento;
        return clonado;

    }

    public static Jogador parseJogador(String csv) {
        String[] dados = csv.split(",", -1); // O parâmetro -1 mantém campos vazios como strings vazias
        int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        int altura = Integer.parseInt(dados[2]);
        int peso = dados[3].isEmpty() ? 0 : Integer.parseInt(dados[3]);
        String universidade = dados[4].isEmpty() ? "N/A" : dados[4];
        int anoNascimento = dados[5].isEmpty() ? 0 : Integer.parseInt(dados[5]);
        String cidadeNascimento = dados.length > 6 && !dados[6].isEmpty() ? dados[6] : "N/A";
        String estadoNascimento = dados.length > 7 && !dados[7].isEmpty() ? dados[7] : "N/A";
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
    }

}

public class Main {
    public static void main(String[] args) {
        Jogador[] jogadores = new Jogador[3923];

        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv"))) {
            br.readLine(); // Pula a primeira linha (cabeçalho)
            String linha;
            int i = 0;
            while ((linha = br.readLine()) != null) {
                jogadores[i] = Jogador.parseJogador(linha);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);
        String entrada;

        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            int id = Integer.parseInt(entrada);
            arvore.inserir(jogadores[id]);
        }

        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            boolean encontrado = arvore.pesquisar(entrada);
            System.out.println(entrada + (encontrado ? " SIM" : " NAO"));
        }

        scanner.close();
    }
}
