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

        public static boolean buscarPorId(List<Personagem> listaPersonagens, String IdProcurado) {
            for (Personagem personagem : listaPersonagens) {
                if (personagem.getName().equalsIgnoreCase(IdProcurado)) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            List<Personagem> listaPersonagens = Personagem.lerDadosDoArquivo(Personagem.FILE_NAME);
            Scanner scanner = new Scanner(System.in);

            // Processamento das entradas para inserção
            String input;
            while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("FIM")) {
                // Processamento das entradas para pesquisa
            }

            while (!(input = scanner.nextLine().trim()).equalsIgnoreCase("FIM")) {
                boolean encontrado = buscarPorId(listaPersonagens, input);
                System.out.println(encontrado ? "SIM" : "NAO");
            }
            scanner.close();
        }

        class Personagem {

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

            // Getters e Setters
            public String getActorName() {
                return actorName;
            }

            public String getAlternateActors() {
                return alternateActors;
            }

            public String getAlternateNames() {
                return alternateNames;
            }

            public String getAncestry() {
                return ancestry;
            }

            public String getDateOfBirth() {
                return dateOfBirth;
            }

            public String getEyeColour() {
                return eyeColour;
            }

            public String getGender() {
                return gender;
            }

            public String getHairColour() {
                return hairColour;
            }

            public String getHouse() {
                return house;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPatronus() {
                return patronus;
            }

            public String getSpecies() {
                return species;
            }

            public int getYearOfBirth() {
                return yearOfBirth;
            }

            public void setActorName(String actorName) {
                this.actorName = actorName;
            }

            public void setAlive(boolean alive) {
                this.alive = alive;
            }

            public void setAlternateActors(String alternateActors) {
                this.alternateActors = alternateActors;
            }

            public void setAlternateNames(String alternateNames) {
                this.alternateNames = alternateNames;
            }

            public void setAncestry(String ancestry) {
                this.ancestry = ancestry;
            }

            public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
            }

            public void setEyeColour(String eyeColour) {
                this.eyeColour = eyeColour;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setHairColour(String hairColour) {
                this.hairColour = hairColour;
            }

            public void setHouse(String house) {
                this.house = house;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPatronus(String patronus) {
                this.patronus = patronus;
            }

            public void setSpecies(String species) {
                this.species = species;
            }

            public void setYearOfBirth(int yearOfBirth) {
                this.yearOfBirth = yearOfBirth;
            }

            // Método para ler dados do arquivo
            public static List<Personagem> lerDadosDoArquivo(String fileName) {
                List<Personagem> personagens = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    br.readLine(); // pula o cabeçalho se existir
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(";");
                        if (data.length == 18) {
                            try {
                                int yearOfBirth = Integer.parseInt(data[13].trim());
                                Personagem personagem = new Personagem(data[0], data[1], data[2], data[3], data[4],
                                        data[5], data[6],
                                        Boolean.parseBoolean(data[7]), Boolean.parseBoolean(data[8]), data[9],
                                        Boolean.parseBoolean(data[10]), data[11], data[12], yearOfBirth,
                                        data[14], data[15], data[16], Boolean.parseBoolean(data[17]));
                                personagens.add(personagem);
                            } catch (NumberFormatException e) {
                                System.err.println("Erro na conversão do ano de nascimento: " + line);
                            }
                        } else {
                            System.err.println("Linha com formato incorreto: " + line);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return personagens;
            }
        }
    }
}