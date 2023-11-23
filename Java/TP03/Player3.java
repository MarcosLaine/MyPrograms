import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Stack {
    private Node top;

    public Node getTopNode() {
        return top;
    }

    public void setTopNode(Node x) {
        this.top = x;
    }

    public void push(Player3 Player3) {
        if (top == null) {
            top = new Node(Player3);
            return;
        }
        Node newNode = new Node(Player3);
        newNode.setNext(top);
        top = newNode;
    }

    public void pop() {
        if (top != null) {
            Node temp = top;
            top = top.getNext();
            System.out.println("(R) " + temp.getData().getNome());
        }
    }

    public void printStack() {
        Node temp = top;
        
        int i = 0;
        while (temp != null) {
            temp = temp.getNext();
            i++;
        }
        int size = i;
        temp = top;
        Player3 Player3Array[] = new Player3[size];
        for(int j = 0; j < size; j++, temp = temp.getNext()) {
            Player3Array[j] = temp.getData();
        }
        i = 0;
        for(int j = size - 1; j >= 0; j--, i++) {
            Player3.imprimir(Player3Array[j], i);
        }
    }
}

class Node {
    Player3 data;
    Node next;

    Node(Player3 Player3) {
        this.data = Player3;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node nextNode) {
        this.next = nextNode;
    }

    public Player3 getData() {
        return this.data;
    }

    public void setData(Player3 Player3) {
        this.data = Player3;
    }
}

public class Player3 {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Player3() {

    }

    Player3(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
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
    public Player3 clonar(Player3 J) {
        Player3 clonado = new Player3();
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
    public static void imprimir(Player3 Player3, int pos) {
        MyIO.println("[" + pos + "]" + " ## " + Player3.getNome() + " ## " + Player3.getAltura() + " ## " +
                Player3.getPeso() + " ## " + Player3.getAnoNascimento() + " ## " + Player3.getUniversidade() +
                " ## " + Player3.getCidadeNascimento() + " ## " + Player3.getEstadoNascimento() + " ##");
    }

    public Player3 ler(Player3 Player3) {
        int aux = MyIO.readInt();
        Player3.setId(aux);
        String aux2 = MyIO.readLine();
        Player3.setNome(aux2);
        aux = MyIO.readInt();
        Player3.setAltura(aux);
        aux = MyIO.readInt();
        Player3.setPeso(aux);
        aux2 = MyIO.readLine();
        Player3.setUniversidade(aux2);
        aux = MyIO.readInt();
        Player3.setAnoNascimento(aux);
        aux2 = MyIO.readLine();
        Player3.setCidadeNascimento(aux2);
        aux2 = MyIO.readLine();
        Player3.setEstadoNascimento(aux2);
        return Player3;
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
        csv = novaString;
        return csv.split(",");
    }

    public static Player3 obterPlayer3PorID(int id, Player3 array[]) {
        return array[id];
    }

    static void corrigirArquivo(String nomeArquivo) {
        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine(); // Adicionando nova linha na entrada
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        corrigirArquivo("pub.in");
        corrigirArquivo("pri.in");
        File arquivo = new File("/tmp/players.csv"); // Substitua pelo caminho do seu arquivo CSV
        Player3 time[] = new Player3[3923];

        try (RandomAccessFile raf = new RandomAccessFile(arquivo, "r")) {
            raf.readLine(); // Pula a primeira linha

            String linha;
            int i = 0;
            // Lê as linhas e instancia os objetos
            while ((linha = raf.readLine()) != null) {
                String[] array = tratarString(linha);

                Player3 Player3 = new Player3();
                Player3.setId(Integer.parseInt(array[0]));
                Player3.setNome(array[1]);
                Player3.setAltura(Integer.parseInt(array[2]));
                Player3.setPeso(Integer.parseInt(array[3]));
                Player3.setUniversidade(array[4]);
                Player3.setAnoNascimento(Integer.parseInt(array[5]));
                Player3.setCidadeNascimento(array[6]);
                Player3.setEstadoNascimento(array[7]);

                time[i] = Player3;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stack pilha = new Stack();

        while (true) {
            String entrada = MyIO.readLine();
            if (entrada.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(entrada);
                pilha.push(time[id]);
            }
        }

        int repeticoes = MyIO.readInt();
        for (int i = 0; i < repeticoes; i++) {
            String entrada = MyIO.readString();

            if (entrada.equals("I")) {
                int id = MyIO.readInt();
                Player3 temp = obterPlayer3PorID(id, time);
                pilha.push(temp);
            } else if (entrada.equals("R")) {
                pilha.pop();
            }
        }

        pilha.printStack();
    }
}
