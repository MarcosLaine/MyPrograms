import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Player {

    // Atributos da classe Player
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;

    // Construtor padrão
    public Player() {
        this.id = -1;
        this.name = "";
        this.height = -1;
        this.weight = -1;
        this.university = "";
        this.birthYear = -1;
        this.birthCity = "";
        this.birthState = "";
    }

    // Construtor com parâmetros
    public Player(int id, String name, int height, int weight, String university, int birthYear, String birthCity, String birthState) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.birthState = birthState;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getUniversity() {
        return university;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

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

    // Método para clonar um jogador
    public Player clone() throws CloneNotSupportedException {
        Player clone = new Player();
        clone.name = this.name;
        clone.height = this.height;
        clone.weight = this.weight;
        clone.university = this.university;
        clone.birthYear = this.birthYear;
        clone.birthCity = this.birthCity;
        clone.birthState = this.birthState;
        return clone;
    }

    // Método para ler e processar dados do jogador
    public void processData(String data) {
        String[] infos = data.split(",");
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].isEmpty()) {
                infos[i] = "nao informado";
            }
        }
        this.setId(Integer.parseInt(infos[0]));
        this.setName(infos[1]);
        this.setHeight(Integer.parseInt(infos[2]));
        this.setWeight(Integer.parseInt(infos[3]));
        this.setUniversity(infos[4]);
        this.setBirthYear(Integer.parseInt(infos[5]));
        this.setBirthCity(infos[6]);
        this.setBirthState(infos[7]);
    }

    // Método para imprimir os dados do jogador
    public void print() {
        System.out.println(
                id + " ## " +
                        name + " ## " +
                        height + " ## " +
                        weight + " ## " +
                        birthYear + " ## " +
                        university + " ## " +
                        birthCity + " ## " +
                        birthState + " ##"
        );
    }

}

class Node {
    Node next;
    Player element;

    Node(Player element) {
        this.element = element;
        this.next = null;
    }
}

class LinkedList {
    Node start, end;

    LinkedList() {
        start = end = null;
    }

    void insert(Player element) {
        Node newNode = new Node(element);
        if (start == null) {
            start = end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }
    }

    boolean search(String name) {
        for (Node i = start; i != null; i = i.next) {
            if (i.element.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    void display() {
        for (Node i = start; i != null; i = i.next) {
            i.element.print();
        }
    }
}

class HashIndireta {
    int size = 25;
    LinkedList[] table = new LinkedList[size];

    HashIndireta() {
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList();
        }
    }

    int hashFunction(Player player) {
        return player.getHeight() % size;
    }

    void add(Player player) {
        int pos = hashFunction(player);
        table[pos].insert(player);
    }

    boolean search(String name) {
        for (LinkedList list : table) {
            if (list.search(name)) {
                return true;
            }
        }
        return false;
    }

    void display() {
        for (LinkedList list : table) {
            list.display();
        }
    }

    public static String[] readFile(String path) {
        String[] dataLines = new String[3922];
        int index = 0;
        try {
            Scanner scanner = new Scanner(new File(path));
            scanner.nextLine(); // Ignora a primeira linha
            while (scanner.hasNext()) {
                dataLines[index++] = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        return dataLines;
    }

    public static void main(String[] args) throws Exception {
        String[] fileData = readFile("/tmp/players.csv");
        Player[] players = new Player[fileData.length];
        Scanner scanner = new Scanner(System.in);
        HashIndireta HashIndireta = new HashIndireta();

        for (int i = 0; i < fileData.length; i++) {
            Player player = new Player();
            player.processData(fileData[i]);
            players[i] = player;
        }

        while (!scanner.hasNext("FIM")) {
            int id = scanner.nextInt();
            HashIndireta.add(players[id]);
        }

        scanner.nextLine(); // Consome a linha "FIM"
        scanner.nextLine(); // Consome a linha vazia

        while (!scanner.hasNext("FIM")) {
            String name = scanner.nextLine();
            System.out.print(name + " ");
            if (HashIndireta.search(name)) {
                System.out.print("SIM\n");
            } else {
                System.out.print("NAO\n");
            }
        }

        scanner.close();
    }

}
