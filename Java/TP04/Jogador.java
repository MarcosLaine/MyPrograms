import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Scanner;

class ArvoreBinaria {

    class No {
        Jogador jogador;
        No esquerda;
        No direita;

        No(Jogador jogador) {
            this.jogador = jogador;
        }
    }

    private No raiz;

    ArvoreBinaria() {
        this.raiz = null;
    }

    public No inserirNo(No noAtual, Jogador jogador) {
        if (noAtual == null) {
            return new No(jogador);
        }
        if (jogador.getNome().compareTo(noAtual.jogador.getNome()) < 0) {
            noAtual.esquerda = inserirNo(noAtual.esquerda, jogador);
        } else {
            noAtual.direita = inserirNo(noAtual.direita, jogador);
        }
        return noAtual;
    }

    public void inserirJogador(Jogador jogador) {
        this.raiz = inserirNo(this.raiz, jogador);
    }

    public void exibirEmOrdem() {
        exibirEmOrdem(this.raiz);
    }

    private void exibirEmOrdem(No no) {
        if (no != null) {
            exibirEmOrdem(no.esquerda);
            System.out.println(no.jogador.getNome());
            exibirEmOrdem(no.direita);
        }
    }

    public void pesquisarJogador(String nome) {
        System.out.print(nome + " raiz");
        pesquisarNo(this.raiz, nome);
    }

    private void pesquisarNo(No no, String nome) {
        if (no == null) {
            System.out.println(" NAO");
            return;
        }
        if (no.jogador.getNome().equals(nome)) {
            System.out.println(" SIM");
        } else if (nome.compareTo(no.jogador.getNome()) < 0) {
            System.out.print(" esq");
            pesquisarNo(no.esquerda, nome);
        } else {
            System.out.print(" dir");
            pesquisarNo(no.direita, nome);
        }
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
    public static int tamanho = 0;

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

    // [3670 ## Peyton Siva ## 183 ## 83 ## 1990 ## University of Louisville ##
    // Seattle ## Washington]
    public static void imprimir(Jogador J, int pos) {

        MyIO.println("[" + pos + "]" + " ## " + J.getNome() + " ## " + J.getAltura() + " ## " + J.getPeso() + " ## "
                + J.getAnoNascimento() + " ## " + J.getUniversidade() + " ## " + J.getCidadeNascimento() + " ## "
                + J.getEstadoNascimento() + " ##");
    }

    public Jogador ler(Jogador J) {
        int aux = MyIO.readInt();
        J.setId(aux);
        String aux2 = MyIO.readLine();
        J.setNome(aux2);
        aux = MyIO.readInt();
        J.setAltura(aux);
        aux = MyIO.readInt();
        J.setPeso(aux);
        aux2 = MyIO.readLine();
        J.setUniversidade(aux2);
        aux = MyIO.readInt();
        J.setAnoNascimento(aux);
        aux2 = MyIO.readLine();
        J.setCidadeNascimento(aux2);
        aux2 = MyIO.readLine();
        J.setEstadoNascimento(aux2);
        return J;
    }

    public static String[] tratarString(String csv) {
        String novaString = "";
        for (int i = 0; i < csv.length(); i++) {
            if (csv.charAt(i) == ',' && i + 1 < csv.length() && csv.charAt(i + 1) == ',') {
                novaString += ",nao informado";
            } else if (csv.charAt(i) == ',' && i == csv.length() - 1) {
                novaString += ",nao informado";
            } else {
                novaString += csv.charAt(i);
            }
        }

        return novaString.split(",");
    }

    public static void main(String[] args) {
        // Criação do array para armazenar os jogadores
        Jogador[] jogadores = new Jogador[3923];

        // Leitura dos dados dos jogadores
        try {
            BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv"));
            br.readLine(); // Pular a primeira linha (cabeçalho)

            String linha;
            int i = 0;
            while ((linha = br.readLine()) != null) {
                jogadores[i] = Jogador.parseJogador(linha);
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inicialização da árvore principal
        ArvoreBinaria arvore = new ArvoreBinaria();
        int[] ordemInsercao = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int chave : ordemInsercao) {
            arvore.inserirNoInicial(chave); // Método para inserir apenas a chave (alturaMod15) na árvore principal
        }

        // Inserção dos jogadores na árvore
        Scanner scanner = new Scanner(System.in);
        String entrada;
        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            int id = Integer.parseInt(entrada);
            arvore.inserir(jogadores[id]);
        }

        // Processamento das buscas
        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            boolean encontrado = arvore.pesquisar(entrada);
            System.out.println(entrada + (encontrado ? " SIM" : " NAO"));
        }

        scanner.close();
    }
}
