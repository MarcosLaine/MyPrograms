import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class ArvoreBinaria {
    Node raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    public void iniciarArvore(int chave) {
        raiz = iniciarNode(raiz, chave);
    }

    private Node iniciarNode(Node Node, int chave) {
        if (Node == null) {
            Node = new Node(chave);
        } else if (chave < Node.dado) {
            Node.esquerda = iniciarNode(Node.esquerda, chave);
        } else if (chave > Node.dado) {
            Node.direita = iniciarNode(Node.direita, chave);
        }
        return Node;
    }

    public void buscarJogador(Jogador jogador) {
        boolean encontrado = false;
        System.out.print("raiz" + " ");
        encontrado = buscar(jogador, raiz, encontrado);
        if (!encontrado) {
            System.out.println("NAO");
        } else {
            System.out.println("SIM");
        }
    }

    private boolean buscar(Jogador jogador, Node Node, boolean encontrado) {
        if (Node != null && !encontrado) {
            encontrado = buscarSubarvore(jogador.getNome(), Node.subRaiz, encontrado);

            if (!encontrado) {
                System.out.print("esq" + " ");
                encontrado = buscar(jogador, Node.esquerda, encontrado);

                System.out.print("dir" + " ");
                encontrado = buscar(jogador, Node.direita, encontrado);
            }
        }
        return encontrado;
    }

    private boolean buscarSubarvore(String nome, NodeSub NodeSub, boolean encontrado) {
        if (!encontrado && NodeSub != null) {
            if (NodeSub.dado.getNome().equals(nome)) {
                encontrado = true;
                return encontrado;
            }

            System.out.print("ESQ" + " ");
            encontrado = buscarSubarvore(nome, NodeSub.esquerda, encontrado);

            System.out.print("DIR" + " ");
            encontrado = buscarSubarvore(nome, NodeSub.direita, encontrado);
        }
        return encontrado;
    }

    public void adicionarJogador(Jogador jogador) {
        raiz = adicionarNode(jogador, raiz);
    }

    private Node adicionarNode(Jogador jogador, Node Node) {
        int alturaMod = jogador.getAltura() % 15;
        if (Node == null) {
            Node = new Node(alturaMod);
        } else if (alturaMod < Node.dado % 15) {
            Node.esquerda = adicionarNode(jogador, Node.esquerda);
        } else if (alturaMod > Node.dado % 15) {
            Node.direita = adicionarNode(jogador, Node.direita);
        } else {
            Node.subRaiz = adicionarSubNode(jogador, Node.subRaiz);
        }
        return Node;
    }

    private NodeSub adicionarSubNode(Jogador jogador, NodeSub NodeSub) {
        if (NodeSub == null) {
            NodeSub = new NodeSub(jogador);
        } else if (jogador.getNome().compareTo(NodeSub.dado.getNome()) < 0) {
            NodeSub.esquerda = adicionarSubNode(jogador, NodeSub.esquerda);
        } else if (jogador.getNome().compareTo(NodeSub.dado.getNome()) > 0) {
            NodeSub.direita = adicionarSubNode(jogador, NodeSub.direita);
        } else {
            System.out.println("Erro ao inserir jogador");
        }
        return NodeSub;
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    private void caminharCentral(Node Node) {
        if (Node != null) {
            caminharCentral(Node.esquerda);
            System.out.println(Node.dado + " ");
            caminharCentral(Node.direita);
        }
    }

    public void caminharPos(Node Node) {
        if (Node != null) {
            caminharPos(Node.esquerda);
            caminharPos(Node.direita);
            System.out.println(Node.dado + " ");
        }
    }
}

class Node {
    Node esquerda, direita;
    int dado;
    NodeSub subRaiz;

    Node(int dado) {
        this.dado = dado;
        esquerda = direita = null;
    }
}

class NodeSub {
    NodeSub esquerda, direita;
    Jogador dado;

    NodeSub(Jogador dado) {
        this.dado = dado;
        esquerda = direita = null;
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
    public static Jogador parseJogador(String linhaCSV) {
        String[] dados = linhaCSV.split(",");
        int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        int altura = Integer.parseInt(dados[2]);
        int peso = Integer.parseInt(dados[3]);
        String universidade = dados[4];
        int anoNascimento = Integer.parseInt(dados[5]);
        String cidadeNascimento = dados.length > 6 ? dados[6] : ""; // Checa se há dados suficientes
        String estadoNascimento = dados.length > 7 ? dados[7] : ""; // Checa se há dados suficientes

        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
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

    public static String[] lerArquivoCSV(String caminho) {
    File arquivo = new File(caminho);
    ArrayList<String> linhas = new ArrayList<>();
    
    try {
        Scanner leitor = new Scanner(arquivo);
        if (leitor.hasNextLine()) { // Pula a primeira linha (cabeçalho)
            leitor.nextLine();
        }
        while (leitor.hasNextLine()) {
            linhas.add(leitor.nextLine());
        }
        leitor.close();
    } catch (FileNotFoundException e) {
        System.out.println("Erro ao abrir arquivo: " + e.getMessage());
    }

    return linhas.toArray(new String[0]); // Converte ArrayList para Array
}


}

public class Principal {

        public static void main(String[] args) throws Exception {
            String[] arrData = Jogador.lerArquivoCSV("/tmp/players.csv");
        Jogador[] jogadorData = new Jogador[arrData.length];
        Scanner teclado = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

            // Inicializando jogadores
            for (int i = 0; i < arrData.length; i++) {
                if (arrData[i] != null && !arrData[i].isEmpty()) {
                    jogadorData[i] = Jogador.parseJogador(arrData[i]);
                }
            }

            // Inicializando árvore binária com valores pré-definidos
            int[] valoresIniciais = { 7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14 };
            for (int valor : valoresIniciais) {
                arvore.iniciarArvore(valor);
            }

            // Inserindo jogadores na árvore
            while (true) {
                String entrada = teclado.nextLine();
                if (entrada.equals("FIM")) {
                    break;
                }
                int id = Integer.parseInt(entrada);
                arvore.adicionarJogador(jogadorData[id]);
            }

            // Buscando jogadores na árvore
            while (true) {
                String entrada = teclado.nextLine();
                if (entrada.equals("FIM")) {
                    break;
                }
                Jogador jogadorProcurado = null;
                for (Jogador jogador : jogadorData) {
                    if (jogador.getNome().equals(entrada)) {
                        jogadorProcurado = jogador;
                        break;
                    }
                }
                if (jogadorProcurado != null) {
                    System.out.print(entrada + " ");
                    arvore.buscarJogador(jogadorProcurado);
                } else {
                    System.out.println("Jogador não encontrado.");
                }
            }

            teclado.close();
        }
    }
