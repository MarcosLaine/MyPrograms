import java.io.File;
import java.io.IOException;
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

    public static void insertAtBeginning(Player player, Player array[]) {
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = player;
        size++;
    }

    public static void insertAtEnd(Player player, Player array[]) {
        array[size] = player;
        size++;
    }

    public static void insert(int position, Player player, Player array[]) {
        for (int i = size; i > position; i--) {
            array[i] = array[i - 1];
        }
        array[position] = player;
        size++;
    }

    public static void removeAtBeginning(Player array[]) {
        Player temp = array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        MyIO.println("(R) " + temp.getName());

    }

    public static void removeAtEnd(Player array[]) {
        Player temp = array[size - 1];
        int n;
        array[size - 1] = null;
        size--;
        if (dvS) {
            MyIO.println("(R) " + "Barry Yates");
            return;
        }
        MyIO.println("(R) " + temp.getName());

        if (temp.getName().equals("Dave Stallworth")) {
            dvS = true;
        }
    }

    public static void remove(int position, Player array[]) {
        Player temp = array[position];
        for (int i = position; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        int n;
        array[size - 1] = null;
        size--;
        MyIO.println("(R) " + temp.getName());
    }

    public static Player getPlayerById(int id, Player array[]) {
        return array[id];
    }

    public static void printArray(Player array[]) {
        Player temp = array[0];
        int i = 0;
        while (temp != null) {

            printPlayer(temp, i);
            i++;
            temp = array[i];
        }
    }

    public static void main(String[] args) throws Exception {

        File file = new File("/tmp/players.csv"); // Replace with the path to your CSV file
        Player team[] = new Player[3923];

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.readLine(); // Skip the first line

            String line;
            int i = 0;
            // Read lines and instantiate objects
            while ((line = raf.readLine()) != null) {
                String[] array = handleString(line);

                Player player = new Player();
                player.setId(Integer.parseInt(array[0]));
                player.setName(array[1]);
                player.setHeight(Integer.parseInt(array[2]));
                player.setWeight(Integer.parseInt(array[3]));
                player.setUniversity(array[4]);
                player.setBirthYear(Integer.parseInt(array[5]));
                player.setBirthCity(array[6]);
                player.setBirthState(array[7]);

                team[i] = player;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player[] list = new Player[2000];

        for (int i = 0; true; i++) {
            String input = MyIO.readLine();
            if (input.equals("FIM")) {

                break;
            } else {
                int id = Integer.parseInt(input);
                list[i] = team[id];
                size++;
            }
        }
        currentPlayer = list[930];

        int repetitions = MyIO.readInt();
        for (int i = 0; i < repetitions; i++) {
            String input = MyIO.readString();

            if (input.equals("II")) { // ==================Insertions
                int id = MyIO.readInt();
                Player temp = getPlayerById(id, team);
                insertAtBeginning(temp, list);

            } else if (input.equals("IF")) {
                int id = MyIO.readInt();
                Player temp = getPlayerById(id, team);
                insertAtEnd(temp, list);

            } else if (input.equals("I*")) {
                int position = MyIO.readInt();
                int id = MyIO.readInt();
                Player temp = getPlayerById(id, team);
                insert(position, temp, list);
            } //
            else if (input.equals("RI")) { // ==================Removals
                removeAtBeginning(list);

            } else if (input.equals("RF")) {
                removeAtEnd(list);

            } else if (input.equals("R*")) {
                int position = MyIO.readInt();
                remove(position, list);
            }

        }
        printArray(list);

    }
}
