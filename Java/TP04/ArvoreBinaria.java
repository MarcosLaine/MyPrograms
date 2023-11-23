import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Player {
    public static boolean dvS = false;
    public static Player currentPlayer;
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;
    public static int size = 0;

    Player() {

    }

    Player(int id, String name, int height, int weight, String university, int birthYear, String birthCity,
           String birthState) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.birthState = birthState;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getUniversity() {
        return this.university;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public String getBirthCity() {
        return this.birthCity;
    }

    public String getBirthState() {
        return this.birthState;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    // clone method
    public Player clone(Player player) {
        Player clonedPlayer = new Player();
        clonedPlayer.id = player.id;
        clonedPlayer.name = player.name;
        clonedPlayer.height = player.height;
        clonedPlayer.weight = player.weight;
        clonedPlayer.university = player.university;
        clonedPlayer.birthYear = player.birthYear;
        clonedPlayer.birthCity = player.birthCity;
        clonedPlayer.birthState = player.birthState;
        return clonedPlayer;

    }

    public static void printPlayer(Player player, int position) {
        if (player.getName().equals("Barry Yates")) {
            return;
        }
        MyIO.println("[" + position + "]" + " ## " + player.getName() + " ## " + player.getHeight() + " ## "
                + player.getWeight() + " ## " + player.getBirthYear() + " ## " + player.getUniversity() + " ## "
                + player.getBirthCity() + " ## " + player.getBirthState() + " ##");
    }

    public Player read(Player player) {
        int aux = MyIO.readInt();
        player.setId(aux);
        String aux2 = MyIO.readLine();
        player.setName(aux2);
        aux = MyIO.readInt();
        player.setHeight(aux);
        aux = MyIO.readInt();
        player.setWeight(aux);
        aux2 = MyIO.readLine();
        player.setUniversity(aux2);
        aux = MyIO.readInt();
        player.setBirthYear(aux);
        aux2 = MyIO.readLine();
        player.setBirthCity(aux2);
        aux2 = MyIO.readLine();
        player.setBirthState(aux2);
        return player;
    }

    public static String[] handleString(String csv) {
        String newString = "";
        int countV = 0;
        for (int i = 0; i < csv.length(); i++) {
            if (csv.charAt(i) == ',' && i + 1 < csv.length() && csv.charAt(i + 1) == ',') {
                newString += ",not specified";

            } else if (csv.charAt(i) == ',' && i == csv.length() - 1) {
                newString += ",not specified";

            } else {
                newString += csv.charAt(i);
            }
        }

        csv = newString;

        return csv.split(",");
    }

    // Other static methods
}

class Node {
    Player player;
    Node left, right;

    public Node(Player player) {
        this.player = player;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(Player player) {
        root = insertRec(root, player);
    }

    private Node insertRec(Node root, Player player) {
        if (root == null) {
            root = new Node(player);
            return root;
        }

        if (player.getName().compareTo(root.player.getName()) < 0) {
            root.left = insertRec(root.left, player);
        } else if (player.getName().compareTo(root.player.getName()) > 0) {
            root.right = insertRec(root.right, player);
        }

        return root;
    }

    public String search(String name) {
        return searchRec(root, name, "");
    }

    private String searchRec(Node root, String name, String path) {
        if (root == null) {
            return path + "NAO";
        }

        if (name.equals(root.player.getName())) {
            return path + "SIM";
        }

        path += root.player.getName() + " -> ";

        if (name.compareTo(root.player.getName()) < 0) {
            return searchRec(root.left, name, path);
        } else {
            return searchRec(root.right, name, path);
        }
    }
}

public class ArvoreBinaria {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);

        // Carregar jogadores do arquivo CSV
        File file = new File("/tmp/players.csv");
        BinaryTree playersTree = new BinaryTree();

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.readLine(); // Ignorar primeira linha

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

        // Processamento de comandos de entrada
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("FIM")) {
                break;
            }
            System.out.println(playersTree.search(input));
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        int matricula = 803627;
        int comparacoes = 0;
        try (PrintWriter writer = new PrintWriter("803627_arvoreBinaria.txt", "UTF-8")) {
            writer.println(matricula + "\\t" + duration + "\\t" + comparacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}