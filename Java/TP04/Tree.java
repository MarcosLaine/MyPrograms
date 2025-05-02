import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


class No {
    Jogador dadosJogador;
    No noDireito, noEsquerdo;

    No() {
        noDireito = noEsquerdo = null;
    }

    No(Jogador x) {
        noDireito = noEsquerdo = null;
        dadosJogador = x;
    }

    // Getters e setters atualizados
    public Jogador getDadosJogador() {
        return this.dadosJogador;
    }

    public void setDadosJogador(Jogador dadosJogador) {
        this.dadosJogador = dadosJogador;
    }

    public No getNoDireito() {
        return this.noDireito;
    }

    public void setNoDireito(No noDireito) {
        this.noDireito = noDireito;
    }

    public No getNoEsquerdo() {
        return this.noEsquerdo;
    }

    public void setNoEsquerdo(No noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
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

    public static Jogador ler(String linhaCSV) {
        String[] campos = tratarString(linhaCSV); // Utiliza o método tratarString para processar a linha CSV
        int id = Integer.parseInt(campos[0]);
        String nome = campos[1];
        int altura = Integer.parseInt(campos[2]);
        int peso = Integer.parseInt(campos[3]);
        String universidade = campos[4];
        int anoNascimento = Integer.parseInt(campos[5]);
        String cidadeNascimento = campos.length > 6 ? campos[6] : "";
        String estadoNascimento = campos.length > 7 ? campos[7] : "";

        return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
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

public class Tree {
    No raiz;

    Tree() {
        raiz = null;
    }

    void adicionar(Jogador x) {
        raiz = adicionar(x, raiz);
    }

    No adicionar(Jogador x, No i) {
        if (i == null) {
            i = new No(x);
        } else if (x.getNome().compareTo(i.getDadosJogador().getNome()) < 0) {
            i.setNoEsquerdo(adicionar(x, i.getNoEsquerdo()));
        } else if (x.getNome().compareTo(i.getDadosJogador().getNome()) > 0) {
            i.setNoDireito(adicionar(x, i.getNoDireito()));
        }
        return i;
    }

    void excluir(Jogador x) {
        raiz = excluir(x, raiz);
    }

    No excluir(Jogador x, No i) {
        if (i == null) {
            // Nó não encontrado
        } else if (x.getNome().compareTo(i.getDadosJogador().getNome()) < 0) {
            i.setNoEsquerdo(excluir(x, i.getNoEsquerdo()));
        } else if (x.getNome().compareTo(i.getDadosJogador().getNome()) > 0) {
            i.setNoDireito(excluir(x, i.getNoDireito()));
        } else {
            if (i.getNoEsquerdo() == null) {
                i = i.getNoDireito();
            } else if (i.getNoDireito() == null) {
                i = i.getNoEsquerdo();
            } else {
                No temp = menorDireito(i);
                i.setDadosJogador(temp.getDadosJogador());
                i.setNoDireito(excluir(temp.getDadosJogador(), i.getNoDireito()));
            }
        }
        return i;
    }

    No menorDireito(No i) {
        No pai = i.getNoDireito();
        No filho = pai;
        while (filho.getNoEsquerdo() != null) {
            pai = filho;
            filho = filho.getNoEsquerdo();
        }
        if (pai == filho) {
            i.setNoDireito(pai.getNoDireito());
        } else {
            pai.setNoEsquerdo(filho.getNoDireito());
        }
        return filho;
    }

    

    boolean buscar(String x) {
        System.out.print(x + " raiz ");
        boolean resp = false;
        if (raiz.getDadosJogador().getNome().equals(x)) {
            resp = true;
        } else if (raiz.getDadosJogador().getNome().compareTo(x) < 0) {
            System.out.print("dir ");
            resp = buscar(raiz.getNoDireito(), x);
        } else if (raiz.getDadosJogador().getNome().compareTo(x) > 0) {
            System.out.print("esq ");
            resp = buscar(raiz.getNoEsquerdo(), x);
        }
        return resp;
    }

    boolean buscar(No i, String x) {
        boolean resp = false;
        if (i != null) {
            if (i.getDadosJogador().getNome().equals(x)) {
                resp = true;
            } else if (i.getDadosJogador().getNome().compareTo(x) < 0) {
                System.out.print("dir ");
                resp = buscar(i.getNoDireito(), x);
            } else if (i.getDadosJogador().getNome().compareTo(x) > 0) {
                System.out.print("esq ");
                resp = buscar(i.getNoEsquerdo(), x);
            }
        }
        return resp;
    }

    void caminharCentral() {
        caminharCentral(raiz);
    }

    void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.getNoEsquerdo());
            System.out.println(i.getDadosJogador().getNome());
            caminharCentral(i.getNoDireito());
        }
    }

    public static String[] lerArquivo(String path) {
        File arquivo = new File(path);
        String[] linhas = new String[3922];
        int i = 0;
        try {
            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine();
            while (scanner.hasNext()) {
                linhas[i] = scanner.nextLine();
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        return linhas;
    }

    public static void main(String[] args) throws Exception {
        String[] dadosArquivo = lerArquivo("/tmp/players.csv");
    Jogador[] jogadores = new Jogador[dadosArquivo.length];
    Scanner entrada = new Scanner(System.in);
    Tree arvore = new Tree();

    for (int i = 0; i < dadosArquivo.length; i++) {
        String linha = dadosArquivo[i];
        Jogador jogador = Jogador.ler(linha); 
        jogadores[i] = jogador;
    }

        while (!entrada.hasNext("FIM")) {
            int indice = entrada.nextInt();
            arvore.adicionar(jogadores[indice]);
        }

        arvore.caminharCentral();
        entrada.close();
    }

}
