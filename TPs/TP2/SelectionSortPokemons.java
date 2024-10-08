import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Existing Pokedex class with an added getPokemonById method 
class Pokedex {
    // private final String FILE_NAME = "C:\\Users\\kino1\\Desktop  \\Programacao\\MyPrograms\\TPs\\TP2\\tmp\\pokemon.csv";
    private static final String FILE_NAME = "/tmp/pokemon.csv";
    public List<Pokemon> listaDePokemons = new ArrayList<>();

    public void lerDadosDoArquivo() {
        listaDePokemons.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            ler.readLine();  // Skip header
            while ((ln = ler.readLine()) != null) {
                // Use regex to split on commas outside quotes
                String[] tokens = ln.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    
                if (tokens.length >= 11) { // Ensure all columns are present
                    String id = tokens[0].trim();
                    int generation = Integer.parseInt(tokens[1].trim());
                    String name = tokens[2].trim();
                    String description = tokens[3].trim();
    
                    // Types
                    String typesString = tokens[4].trim().replaceAll("[\\[\\]'\"]", "");
                    ArrayList<String> types = new ArrayList<>(Arrays.asList(typesString.split(", ")));
    
                    // Abilities
                    String abilitiesString = tokens[5].trim().replaceAll("[\\[\\]'\"]", "");
                    ArrayList<String> abilities = new ArrayList<>(Arrays.asList(abilitiesString.split(", ")));
    
                    double weight = Double.parseDouble(tokens[6].trim());
                    double height = Double.parseDouble(tokens[7].trim());
                    int captureRate = Integer.parseInt(tokens[8].trim().replace("%", ""));
                    boolean isLegendary = tokens[9].trim().equals("1");
    
                    Date captureDate = null;
                    try {
                        captureDate = dateFormat.parse(tokens[10].trim());
                    } catch (ParseException e) {
                        System.out.println("Erro ao parsear a data: " + tokens[10].trim());
                    }
    
                    Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, isLegendary, captureDate);
                    listaDePokemons.add(pokemon);
                } else {
                    System.out.println("Linha mal formatada ou incompleta: " + ln);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
    

    public Pokemon getPokemonById(String idStr) {
        String id = idStr.trim();
        for (Pokemon p : listaDePokemons) {
            if (p.getId().trim().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
}

// Existing Pokemon class
class Pokemon {
    public String id;
    public int generation;
    public String name;
    public String description;
    public ArrayList<String> types;
    public ArrayList<String> abilities;
    public double weight;
    public double height;
    public int captureRate;
    public boolean isLegendary;
    public Date captureDate;

    public Pokemon(String id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities,
                   double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    public Pokemon() {
        types = new ArrayList<>();
        abilities = new ArrayList<>();
    }

    // Setter methods
    public void setId(String id) {
        this.id = id;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypes(List<String> types) {
        this.types = (ArrayList<String>) types;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = (ArrayList<String>) abilities;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public int getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void imprimir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = (captureDate != null) ? dateFormat.format(captureDate) : "Data não informada";

        System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

        // Print types
        for (int i = 0; i < types.size(); i++) {
            System.out.print("'" + types.get(i) + "'");
            if (i < types.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] - [");

        // Print abilities
        for (int i = 0; i < abilities.size(); i++) {
            System.out.print("" + abilities.get(i) + "");
            if (i < abilities.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] - ");

        System.out.printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
                weight, height, captureRate,
                isLegendary ? "true" : "false",
                generation, formattedDate);
    }

}

// Implemented SelectionSortPokemons class
public class SelectionSortPokemons {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        pokedex.lerDadosDoArquivo();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonsList = new ArrayList<>();
        ArrayList<Pokemon> pokemonsEscolhidos = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("FIM")) {
                break;
            }
            String id = line.trim();
            
            Pokemon pokemon = pokedex.getPokemonById(id);
            if (pokemon != null) {
                pokemonsList.add(pokemon);
            } else {
                System.out.println("Pokemon com ID " + id + " não encontrado.");
            }
        }

        // Convert ArrayList to array
        // Pokemon[] pokemonsArray = pokemonsList.toArray(new Pokemon[0]);

        // Perform selection sort on pokemonsArray based on name
        ordenacaoPorSelecao(pokemonsEscolhidos);

        // Print the sorted array in specified format
        for (Pokemon p : pokemonsEscolhidos) {
            p.imprimir();
        }
    }

    public static void ordenacaoPorSelecao(List<Pokemon> vetor) {
        int n = vetor.size();
        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                // comparacoes++; // Incrementa o número de comparações
                if (vetor.get(j).name.compareTo(vetor.get(menorIndice).name) < 0) {
                    menorIndice = j;
                }
            }
            if (i != menorIndice) {
                Collections.swap(vetor, i, menorIndice);
                // movimentacoes++; // Incrementa o número de movimentações (trocas)
            }
        }
    }

    /*public static void selectionSortByName(Pokemon[] arr) {
        int n = arr.length;
    
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
    
            for (int j = i + 1; j < n; j++) {
                // Use compareToIgnoreCase for case-insensitive comparison
                if (arr[j].getName().compareToIgnoreCase(arr[min_idx].getName()) < 0) {
                    min_idx = j;
                }
            }
    
            // Swap arr[min_idx] and arr[i] if min_idx is different
            if (min_idx != i) {
                Pokemon temp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = temp;
            }
        }
    }*/
    
}