
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class BinaryTree {
    private Player3Node root;

    public void insert(Player3 player) {
        root = insertRec(root, player);
    }

    private Player3Node insertRec(Player3Node root, Player3 player) {
        if (root == null) {
            root = new Player3Node(player);
            return root;
        }

        if (player.getNome().compareTo(root.getPlayer3().getNome()) < 0) {
            root.left = insertRec(root.left, player);
        } else if (player.getNome().compareTo(root.getPlayer3().getNome()) > 0) {
            root.right = insertRec(root.right, player);
        }

        return root;
    }

    public String search(String nome) {
        return searchRec(root, nome, "");
    }

    private String searchRec(Player3Node root, String nome, String path) {
        if (root == null) {
            return path + " NAO";
        }

        if (nome.equals(root.getPlayer3().getNome())) {
            return path + " SIM";
        }

        if (nome.compareTo(root.getPlayer3().getNome()) < 0) {
            return searchRec(root.left, nome, path + " esq");
        } else {
            return searchRec(root.right, nome, path + " dir");
        }
    }
}

class Player3Node {
    Player3 player;
    Player3Node left, right;

    Player3Node(Player3 player) {
        this.player = player;
        left = right = null;
    }

    public Player3 getPlayer3() {
        return player;
    }
}

class Player3 {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Player3() {}

    Player3(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // Getters and setters as in your original code
    // ...

    public static String[] tratarString(String csv) {
        // Implementação do método tratarString como no seu código original
        // ...
    }

    public static Player3 obterPlayer3PorID(int id, Player3 array[]) {
        return array[id];
    }

    // Outros métodos da classe Player3 conforme o seu código original
    // ...
}

public class BinaryTreePlayer {
    public static void main(String[] args) throws Exception {
        // Inicialização e leitura do arquivo, conforme o código original
        // ...

        BinaryTree tree = new BinaryTree();

        // Inserir elementos na árvore
        // for (Player3 player : playersArray) { // Supondo um array de Player3
        //    tree.insert(player);
        // }

        // Realizar pesquisas na árvore e imprimir os caminhos
        // while (true) {
        //    String entrada = MyIO.readLine();
        //    if (entrada.equals("FIM")) {
        //        break;
        //    } else {
        //        String resultado = tree.search(entrada);
        //        System.out.println(resultado);
        //    }
        // }

        // Criar o arquivo de log
        long startTime = System.nanoTime();
        // Executar as operações de busca
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Converter para milissegundos
        int comparacoes = 0; // calcular o número de comparações;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("matricula_arvoreBinaria.txt"))) {
            writer.write("Matricula" + "\t" + duration + "\t" + comparacoes);
        }
    }
}
