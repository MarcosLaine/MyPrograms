import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaSequencial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Personagem> personagens = Personagem.lerDadosDoArquivo(Personagem.FILE_NAME);

        ArrayList<String> nomesParaBuscar = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();

            if (linha.equalsIgnoreCase("FIM")) {
                break;
            } else {
                nomesParaBuscar.add(linha);
            }
        }

        for (String nomeBusca : nomesParaBuscar) {
            boolean encontrado = false;
            for (Personagem personagem : personagens) {
                if (personagem.getName().equalsIgnoreCase(nomeBusca)) {
                    encontrado = true;
                    break;
                }
            }
            System.out.println(encontrado ? "SIM" : "NAO");
        }

        scanner.close();
    }


    static class Personagem {
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

        public Personagem(String id, String name, String alternateNames, String house, String ancestry,
                          String species, String patronus, boolean hogwartsStaff, boolean hogwartsStudent,
                          String actorName, boolean alive, String alternateActors, String dateOfBirth,
                          int yearOfBirth, String eyeColour, String gender, String hairColour, boolean wizard) {
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

        
        public String getName() {
            return name;
        }


        // Método para ler dados do arquivo
        public static List<Personagem> lerDadosDoArquivo(String fileName) {
            List<Personagem> personagens = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine(); // Lê a primeira linha
                
                // Verifica se a primeira linha é um cabeçalho
                if (line != null && line.toLowerCase().contains("id")) {
                    line = br.readLine(); // Pula a linha do cabeçalho
                }
                
                while (line != null) {
                    String[] data = line.split(";");
                    if (data.length == 18) {
                        try {
                            // Utiliza uma expressão regular para verificar se o ano de nascimento é numérico
                            int yearOfBirth = data[13].matches("-?\\d+(\\.\\d+)?") ? Integer.parseInt(data[13]) : -1;
                            Personagem personagem = new Personagem(
                                data[0], data[1], data[2], data[3], data[4], data[5], data[6],
                                Boolean.parseBoolean(data[7]), Boolean.parseBoolean(data[8]), data[9],
                                Boolean.parseBoolean(data[10]), data[11], data[12], yearOfBirth,
                                data[14], data[15], data[16], Boolean.parseBoolean(data[17])
                            );
                            personagens.add(personagem);
                        } catch (NumberFormatException e) {
                            System.err.println("Erro na conversão de dados: " + line);
                        }
                    } else {
                        System.err.println("Linha com formato incorreto: " + line);
                    }
                    line = br.readLine(); // Lê a próxima linha
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return personagens;
        }
    }
}
