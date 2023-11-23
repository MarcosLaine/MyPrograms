import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class DoublyLinkedList {
    class Node {
        Node next;
        Node prev;
        QuicksortJogador value;

        Node(QuicksortJogador x) {
            value = x;
            next = prev = null;
        }
    }

    Node first;
    Node last;

    DoublyLinkedList() {
        first = last = null;
    }

    Node createNode(QuicksortJogador x) {
        return new Node(x);
    }

    public void insert(QuicksortJogador x) {
        if (first == null) {
            first = last = createNode(x);
        } else {
            Node temp = last.next = createNode(x);
            temp.prev = last;
            last = temp;
        }
    }

    public void printList() {
        Node temp = first;
        while (temp != null) {
            QuicksortJogador.print(temp.value);
            temp = temp.next;
        }
    }

    public int size() {
        int count = 0;
        Node temp = first;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public QuicksortJogador[] toArray() {
        QuicksortJogador[] array = new QuicksortJogador[this.size()];
        Node temp = first;
        int i = 0;
        while (temp != null) {
            array[i] = temp.value;
            i++;
            temp = temp.next;
        }
        return array;
    }

    public static DoublyLinkedList arrayToLinkedList(QuicksortJogador[] array) {
        DoublyLinkedList list = new DoublyLinkedList();
        for (int i = 0; i < array.length; i++) {
            list.insert(array[i]);
        }
        return list;
    }

    public void quickSort() {
        QuicksortJogador[] array = this.toArray();
        quickSort(array, 0, size() - 1);
        DoublyLinkedList temp = arrayToLinkedList(array);
        this.first = temp.first;
        this.last = temp.last;
    }

    public int compare(QuicksortJogador[] array, int i, QuicksortJogador pivot) {
        int result = array[i].getBirthState().compareTo(pivot.getBirthState());
        if (result != 0) {
            return result;
        }
        return array[i].getName().compareTo(pivot.getName());
    }

    public void quickSort(QuicksortJogador[] array, int left, int right) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (compare(array, i, array[j]) < 0) {
                    QuicksortJogador temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}

class QuicksortJogador {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;

    QuicksortJogador() {
    }

    QuicksortJogador(int id, String name, int height, int weight, String university, int birthYear,
            String birthCity,
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

    // Getters
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

    // Setters
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

    // Clone
    public QuicksortJogador clone(QuicksortJogador J) {
        QuicksortJogador cloned = new QuicksortJogador();
        cloned.id = J.id;
        cloned.name = J.name;
        cloned.height = J.height;
        cloned.weight = J.weight;
        cloned.university = J.university;
        cloned.birthYear = J.birthYear;
        cloned.birthCity = J.birthCity;
        cloned.birthState = J.birthState;
        return cloned;
    }

    // [3670 ## Peyton Siva ## 183 ## 83 ## 1990 ## University of Louisville ##
    // Seattle ## Washington]
    public static void print(QuicksortJogador J) {
        System.out.println("[" + J.getId() + " ## " + J.getName() + " ## " + J.getHeight() + " ## " + J.getWeight()
                + " ## " + J.getBirthYear() + " ## " + J.getUniversity() + " ## " + J.getBirthCity() + " ## "
                + J.getBirthState() + "]");
    }

    public QuicksortJogador read(QuicksortJogador J) {
        Scanner scanner = new Scanner(System.in);
        int aux = scanner.nextInt();
        J.setId(aux);
        String aux2 = scanner.nextLine();
        J.setName(aux2);
        aux = scanner.nextInt();
        J.setHeight(aux);
        aux = scanner.nextInt();
        J.setWeight(aux);
        aux2 = scanner.nextLine();
        J.setUniversity(aux2);
        aux = scanner.nextInt();
        J.setBirthYear(aux);
        aux2 = scanner.nextLine();
        J.setBirthCity(aux2);
        aux2 = scanner.nextLine();
        J.setBirthState(aux2);
        return J;
    }

    public static String[] handleString(String csv) {
        String newString = "";
        for (int i = 0; i < csv.length(); i++) {
            if (csv.charAt(i) == ',' && i + 1 < csv.length() && csv.charAt(i + 1) == ',') {
                newString += ",nao informado";
            } else if (csv.charAt(i) == ',' && i == csv.length() - 1) {
                newString += ",nao informado";
            } else {
                newString += csv.charAt(i);
            }
        }

        csv = newString;

        return csv.split(",");
    }

    public static QuicksortJogador getQuicksortJogadorByID(int id, QuicksortJogador[] array) {
        return array[id];
    }

    public static void main(String[] args) throws Exception {

        File file = new File("/tmp/players.csv");
        QuicksortJogador[] team = new QuicksortJogador[3923];

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.readLine(); // Pula a primeira linha

            String line;
            int i = 0;
            // Lê as linhas e instancia os objetos
            while ((line = raf.readLine()) != null) {
                String[] array = handleString(line);

                QuicksortJogador jogador = new QuicksortJogador();
                jogador.setId(Integer.parseInt(array[0]));
                jogador.setName(array[1]);
                jogador.setHeight(Integer.parseInt(array[2]));
                jogador.setWeight(Integer.parseInt(array[3]));
                jogador.setUniversity(array[4]);
                jogador.setBirthYear(Integer.parseInt(array[5]));
                jogador.setBirthCity(array[6]);
                jogador.setBirthState(array[7]);

                team[i] = jogador;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DoublyLinkedList list = new DoublyLinkedList();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) { // Verifica se há próxima linha antes de ler
            String entry = scanner.nextLine();
            if (entry.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(entry);
                list.insert(team[id]);
            }
        }
        list.quickSort();
        list.printList();
    }
}
