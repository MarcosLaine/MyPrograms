import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Selecao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> idsEntrada = new ArrayList<>();
        String linhaEntrada;

        // Ler IDs até a palavra "FIM"
        while (!(linhaEntrada = scanner.nextLine().trim()).equalsIgnoreCase("FIM")) {
            idsEntrada.add(linhaEntrada);
        }

        List<Personagem> todosPersonagens = lerDadosDoArquivo(Personagem.FILE_NAME);
        List<Personagem> personagensSelecionados = new ArrayList<>();

        // Filtrar personagens com base nos IDs lidos
        for (String id : idsEntrada) {
            for (Personagem personagem : todosPersonagens) {
                if (personagem.getId().equals(id)) {
                    personagensSelecionados.add(personagem);
                    break;
                }
            }
        }

        // Ordenação por nome
        ordenarPorNome(personagensSelecionados);

        // Imprimir personagens ordenados pelo nome
        for (Personagem personagem : personagensSelecionados) {
            System.out.println(personagem);
        }

        scanner.close();
    }

    // Método de ordenação por seleção
    public static void ordenarPorNome(List<Personagem> personagens) {
        for (int i = 0; i < personagens.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < personagens.size(); j++) {
                if (personagens.get(j).getName().compareToIgnoreCase(personagens.get(index).getName()) < 0) {
                    index = j;
                }
            }
            if (index != i) {
                Personagem temp = personagens.get(index);
                personagens.set(index, personagens.get(i));
                personagens.set(i, temp);
            }
        }
    }

    public static List<Personagem> lerDadosDoArquivo(String fileName) {
        List<Personagem> personagens = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Pula a primeira linha (cabeçalho)
    
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";"); 
    
                // Verificar se a linha tem o número esperado de campos
                if (data.length < 18) {
                    System.err.println("Linha incompleta: " + line);
                    continue; // Pula esta linha e vai para a próxima
                }
    
                // Cria o objeto Personagem e adiciona na lista
                personagens.add(new Personagem(
                    data[0],  // id
                    data[1],  // name
                    data[2],  // alternateNames
                    data[3],  // house
                    data[4],  // ancestry
                    data[5],  // species
                    data[6],  // patronus
                    Boolean.parseBoolean(data[7]),  // hogwartsStaff
                    Boolean.parseBoolean(data[8]),  // hogwartsStudent
                    data[9],  // actorName
                    Boolean.parseBoolean(data[10]), // alive
                    data[11], // alternateActors
                    data[12], // dateOfBirth
                    parseIntSafe(data[13]), // yearOfBirth
                    data[14], // eyeColour
                    data[15], // gender
                    data[16], // hairColour
                    Boolean.parseBoolean(data[17])  // wizard
                ));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return personagens;
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor padrão se a conversão falhar
        }
    }

    public static class Personagem {
        static final String FILE_NAME = "/tmp/characters.csv";

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

        public Personagem(String id, String name, String alternateNames, String house,
                String ancestry, String species, String patronus, boolean hogwartsStaff,
                boolean hogwartsStudent, String actorName, boolean alive,
                String alternateActors, String dateOfBirth, int yearOfBirth,
                String eyeColour, String gender, String hairColour, boolean wizard) {
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

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        } 
 
        @Override
        public String toString() {
            return "[" + id + " ## " + name + " ## " + alternateNames + " ## " + house + " ## " +
                    ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + " ## " +
                    hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + dateOfBirth + " ## " +
                    yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour + " ## " +
                    wizard + "]";
        }

    }
}
