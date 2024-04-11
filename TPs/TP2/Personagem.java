import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Definição da classe Personagem
public class Personagem {
    private String id;
    private String name;
    private String alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private String alternateActors;
    private String dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    // Lista para armazenar os objetos Personagem
    private static List<Personagem> listaPersonagens = new ArrayList<>();

    // Nome do arquivo CSV que contém os dados dos personagens
    private static final String FILE_NAME = "/tmp/characters.csv";

    // Construtor parametrizado para inicializar os atributos de Personagem
    public Personagem(String id, String name, String alternateNames, String house, String ancestry, String species,
                      String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName, boolean alive,
                      String alternateActors, String dateOfBirth, int yearOfBirth, String eyeColour, String gender,
                      String hairColour, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternateNames = alternateNames;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.alternateActors = alternateActors;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    // Método imprimir para exibir os detalhes de um personagem
    public void imprimir() {
        System.out.print("[" + id);
        System.out.print(" ## " + name);
        System.out.print(" ## " + alternateNames);
        System.out.print(" ## " + house);
        System.out.print(" ## " + ancestry);
        System.out.print(" ## " + species);
        System.out.print(" ## " + patronus);
        System.out.print(" ## " + hogwartsStaff);
        System.out.print(" ## " + hogwartsStudent);
        System.out.print(" ## " + actorName);
        System.out.print(" ## " + alive);
        System.out.print(" ## " + alternateActors);
        System.out.print(" ## " + dateOfBirth);
        System.out.print(" ## " + yearOfBirth);
        System.out.print(" ## " + eyeColour);
        System.out.print(" ## " + gender);
        System.out.print(" ## " + hairColour);
        System.out.print(" ## " + wizard);
        System.out.println("]");
    }

    // Método estático para ler dados do arquivo CSV e preencher a lista de Personagem
    public static void lerDadosDoArquivo() {
        listaPersonagens.clear();
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) {
                String[] atributo = ln.split(";");
                if (atributo.length == 18) { // Garante que todas as 18 colunas estão presentes
                    String id = atributo[0].trim();
                    String name = atributo[1].trim();
                    String alternateNames = atributo[2].trim();
                    String house = atributo[3].trim();
                    String ancestry = atributo[4].trim();
                    String species = atributo[5].trim();
                    String patronus = atributo[6].trim();
                    boolean hogwartsStaff = Boolean.parseBoolean(atributo[7].trim());
                    boolean hogwartsStudent = Boolean.parseBoolean(atributo[8].trim());
                    String actorName = atributo[9].trim();
                    boolean alive = Boolean.parseBoolean(atributo[10].trim());
                    String alternateActors = atributo[11].trim();
                    String dateOfBirth = atributo[12].trim();
                    int yearOfBirth = Integer.parseInt(atributo[13].trim());
                    String eyeColour = atributo[14].trim();
                    String gender = atributo[15].trim();
                    String hairColour = atributo[16].trim();
                    boolean wizard = Boolean.parseBoolean(atributo[17].trim());
    
                    Personagem personagem = new Personagem(id, name, alternateNames, house, ancestry, species, patronus,
                            hogwartsStaff, hogwartsStudent, actorName, alive, alternateActors, dateOfBirth, yearOfBirth,
                            eyeColour, gender, hairColour, wizard);
                    listaPersonagens.add(personagem);
                } else {
                    System.out.println("Linha mal formatada ou incompleta: " + ln);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    // Método main
    public static void main(String[] args) {
        lerDadosDoArquivo(); // Chama o método para ler dados do arquivo CSV
        // Loop para criar objetos Personagem a partir da entrada do usuário
        while (true) {
            String input = MyIO.readLine().trim();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            String personagemId = input;
            boolean personagemEncontrado = false;

            for (Personagem personagem : listaPersonagens) {
                if (personagem.id.equals(personagemId)) {
                    personagemEncontrado = true;
                    personagem.imprimir();
                    break;
                }
            }
            if (!personagemEncontrado) {
                System.out.println("Personagem não encontrado");
            }
        }
    }
}
