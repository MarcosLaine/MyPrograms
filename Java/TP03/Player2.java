import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class LinkedList {
    private Node last;
    private Node first;

    public Node getLast() {
        return last;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node x) {
        this.first = x;
    }

    public void setLast(Node x) {
        this.last = x;
    }

    public void insertAtBeginning(Player2 x) {
        if (first == null) {
            first = new Node(x);
            return;
        }
        Node temp = new Node(x);
        temp.setNext(first);
        first = temp;
    }

    public void insertAtEnd(Player2 x) {
        if (first == null) {
            first = last = new Node(x);
        } else {
            last.setNext(new Node(x));
            last = last.getNext();
        }
    }

    public void insert(int pos, Player2 x) {
        Node temp = first;
        for (int i = 1; i < pos; i++) { 
            if (temp == null)
                return;
            temp = temp.getNext();
        }
        Node port = temp.getNext();
        temp.setNext(new Node(x));
        temp.getNext().setNext(port);
    }

    public void removeAtBeginning() {
        if (first != null) {
            System.out.println("(R) " + first.getValue().getName());
            first = first.getNext();
        }
    }

    public void removeAtEnd() {
        if (last != null) {
            System.out.println("(R) " + last.getValue().getName());
            if (first == last) {
                last = first = null;
                return;
            }
            Node temp = first;
            while (temp.getNext() != last) {
                temp = temp.getNext();
            }
            last = temp;
            temp.setNext(null);
        }
    }

    public void remove(int pos) {
        Node temp = first;
        for (int i = 1; i < pos; i++) {
            temp = temp.getNext();
        }
        System.out.println("(R) " + temp.getNext().getValue().getName());
        temp.setNext(temp.getNext().getNext());
    }

    public void printList() {
        Node temp = first;
        int i = 0;
        while (temp != null) {
            Player2.print(temp.getValue(), i);
            temp = temp.getNext();
            i++;
        }
    }
}

class Node {
    Player2 value;
    Node next;

    Node(Player2 x) {
        this.value = x;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node x) {
        this.next = x;
    }

    public Player2 getValue() {
        return this.value;
    }

    public void setValue(Player2 x) {
        this.value = x;
    }
}

public class Player2 {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;
    public static int size = 0;

    Player2() {

    }

    Player2(int id, String name, int height, int weight, String university, int birthYear, String birthCity,
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

    // getters
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

    // clone
    public Player2 clone(Player2 P) {
        Player2 cloned = new Player2();
        cloned.id = P.id;
        cloned.name = P.name;
        cloned.height = P.height;
        cloned.weight = P.weight;
        cloned.university = P.university;
        cloned.birthYear = P.birthYear;
        cloned.birthCity = P.birthCity;
        cloned.birthState = P.birthState;
        return cloned;
    }

    public static void print(Player2 P, int pos) {
        MyIO.println("[" + pos + "]" + " ## " + P.getName() + " ## " + P.getHeight() + " ## " + P.getWeight() + " ## "
                + P.getBirthYear() + " ## " + P.getUniversity() + " ## " + P.getBirthCity() + " ## "
                + P.getBirthState() + " ##");
    }

    public Player2 read(Player2 P) {
        int aux = MyIO.readInt();
        P.setId(aux);
        String aux2 = MyIO.readLine();
        P.setName(aux2);
        aux = MyIO.readInt();
        P.setHeight(aux);
        aux = MyIO.readInt();
        P.setWeight(aux);
        aux2 = MyIO.readLine();
        P.setUniversity(aux2);
        aux = MyIO.readInt();
        P.setBirthYear(aux);
        aux2 = MyIO.readLine();
        P.setBirthCity(aux2);
        aux2 = MyIO.readLine();
        P.setBirthState(aux2);
        return P;
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

    public static Player2 getPlayer2ByID(int id, Player2 array[]) {
        return array[id];
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/tmp/players.csv");
        Player2 team[] = new Player2[3923];

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.readLine(); 

            String line;
            int i = 0;

            while ((line = raf.readLine()) != null) {
                String[] array = handleString(line);

                Player2 player2 = new Player2();
                player2.setId(Integer.parseInt(array[0]));
                player2.setName(array[1]);
                player2.setHeight(Integer.parseInt(array[2]));
                player2.setWeight(Integer.parseInt(array[3]));
                player2.setUniversity(array[4]);
                player2.setBirthYear(Integer.parseInt(array[5]));
                player2.setBirthCity(array[6]);
                player2.setBirthState(array[7]);

                team[i] = player2;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedList list = new LinkedList();

        while (true) {
            String input = MyIO.readLine();
            if (input.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(input);
                list.insertAtEnd(team[id]);
                size++;
            }
        }

        int repetitions = MyIO.readInt();
        for (int i = 0; i < repetitions; i++) {
            String input = MyIO.readString();

            if (input.equals("II")) { 
                int id = MyIO.readInt();
                Player2 temp = getPlayer2ByID(id, team);
                list.insertAtBeginning(temp);
            } else if (input.equals("IF")) {
                int id = MyIO.readInt();
                Player2 temp = getPlayer2ByID(id, team);
                list.insertAtEnd(temp);
            } else if (input.equals("I*")) {
                int pos = MyIO.readInt();
                int id = MyIO.readInt();
                Player2 temp = getPlayer2ByID(id, team);
                list.insert(pos, temp);
            } else if (input.equals("RI")) { 
                list.removeAtBeginning();
            } else if (input.equals("RF")) {
                list.removeAtEnd();
            } else if (input.equals("R*")) {
                int pos = MyIO.readInt();
                list.remove(pos);
            }
        }
        list.printList();
    }
}
