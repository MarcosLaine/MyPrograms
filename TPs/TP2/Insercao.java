import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Insercao {

    public static void main(String[] args) {
        List<Personagem> personagens = Personagem.lerDadosDoArquivo(Personagem.FILE_NAME);
        
        if (personagens == null || personagens.isEmpty()) {
            System.out.println("Nenhum personagem encontrado ou erro ao ler o arquivo.");
            return;
        }
        
        // Ordenação por seleção
        ordenarPorSelecao(personagens);
        
        // Imprimir os personagens ordenados
        personagens.forEach(System.out::println);

        
    }

    // Implementação do algoritmo de ordenação por seleção
    public static void ordenarPorSelecao(List<Personagem> personagens) {
        for (int i = 0; i < personagens.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < personagens.size(); j++) {
                if (personagens.get(j).getDateOfBirth().compareTo(personagens.get(index).getDateOfBirth()) < 0) {
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

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }


        // Método para ler dados do arquivo
        public static List<Personagem> lerDadosDoArquivo(String fileName) {
            List<Personagem> personagens = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine(); // Lê a primeira linha, supondo que seja o cabeçalho
        
                while ((line = br.readLine()) != null) { // Lê da segunda linha em diante
                    String[] data = line.split(",");
                    if (data.length != 18) { // Verifica se a linha tem exatamente 18 colunas
                        System.err.println("Linha com formato incorreto: " + line);
                        continue; // Pula para a próxima iteração do loop se não tiver 18 colunas
                    }
        
                    try {
                        int yearOfBirth = data[13].matches("-?\\d+(\\.\\d+)?") ? Integer.parseInt(data[13]) : -1;
                        Personagem personagem = new Personagem(
                            data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                            data[5].trim(), data[6].trim(), Boolean.parseBoolean(data[7].trim()),
                            Boolean.parseBoolean(data[8].trim()), data[9].trim(),
                            Boolean.parseBoolean(data[10].trim()), data[11].trim(), data[12].trim(),
                            yearOfBirth, data[14].trim(), data[15].trim(), data[16].trim(),
                            Boolean.parseBoolean(data[17].trim())
                        );
                        personagens.add(personagem);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro na conversão de dados: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return personagens;
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
