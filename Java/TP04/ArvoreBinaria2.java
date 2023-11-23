
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Player {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;

    // Constructor
    Player(int id, String name, int height, int weight, String university, int birthYear, String birthCity, String birthState) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.birthState = birthState;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    // Method to handle CSV strings
    public static String[] handleString(String csv) {
        String newString = "";
        for (int i = 0; i < csv.length(); i++) {
            if (csv.charAt(i) == ',' && (i + 1 >= csv.length() || csv.charAt(i + 1) == ',')) {
                newString += ",not specified";
            } else {
                newString += csv.charAt(i);
            }
        }
        return newString.split(",");
    }
}

class Node {
    Player player;
    Node left, right;

    Node(Player player) {
        this.player = player;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    private Node root;
    private int comparisons;

    BinaryTree() {
        root = null;
        comparisons = 0;
    }

    public void insert(Player player) {
        root = insertRec(root, player);
    }

    private Node insertRec(Node root, Player player) {
        if (root == null) {
            root = new Node(player);
            return root;
        }

        comparisons++;
        if (player.getName().compareTo(root.player.getName()) < 0) {
            root.left = insertRec(root.left, player);
        } else if (player.getName().compareTo(root.player.getName()) > 0) {
            root.right = insertRec(root.right, player);
        }

        return root;
    }

    public String search(String name) {
        return searchRec(root, name, "raiz ");
    }

    private String searchRec(Node root, String name, String path) {
        if (root == null) {
            comparisons++;
            return path + "NAO";
        }

        comparisons++;
        if (name.equals(root.player.getName())) {
            return path + "SIM";
        }

        if (name.compareTo(root.player.getName()) < 0) {
            return searchRec(root.left, name, path + "esq ");
        } else {
            return searchRec(root.right, name, path + "dir ");
        }
    }

    public int getComparisons() {
        return comparisons;
    }
}

public class ArvoreBinaria2 {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        BinaryTree playersTree = new BinaryTree();

        try (RandomAccessFile raf = new RandomAccessFile(new File("/tmp/players.csv"), "r")) {
            raf.readLine(); // Skip header

            String line;
            while ((line = raf.readLine()) != null) {
                String[] array = Player.handleString(line);
                Player player = new Player(
                    Integer.parseInt(array[0]), array[1],
                    Integer.parseInt(array[2]), Integer.parseInt(array[3]),
                    array[4], Integer.parseInt(array[5]), array[6], array[7]
                );
                playersTree.insert(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("FIM")) {
                break;
            }
            System.out.println(playersTree.search(input));
        }

        long endTime = System.currentTimeMillis();
        try (PrintWriter writer = new PrintWriter("803627_arvoreBinaria.txt", "UTF-8")) {
            writer.println("803627\t" + (endTime - startTime) + "\t" + playersTree.getComparisons());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
