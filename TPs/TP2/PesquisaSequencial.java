import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PesquisaSequencial {
    public static LinkedList<Pokemon> Lista = new LinkedList<>();

    public static void verificaPesquisa(String nome){
        boolean achou = false;
        for(Pokemon pokemon : PesquisaSequencial.Lista){
            if(pokemon.getName().equals(nome)){
                achou = true;
                break; // Optional: Exit the loop early if found
            }
        }
        if(achou)
            System.out.println("SIM");
        else
            System.out.println("NAO");
    }
    

    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        pokedex.lerDadosDoArquivo();
        Scanner sc = new Scanner(System.in);
    
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            boolean pokemonEncontrado = false;
    
            for (Pokemon pokemon : pokedex.listaDePokemons) {
                if (pokemon.getId().equals(entrada)) {
                    PesquisaSequencial.Lista.add(pokemon);
                    pokemonEncontrado = true;
                    break;
                }
            }
    
            if (!pokemonEncontrado) {
                System.out.println("Pokemon não encontrado");
            }
            entrada = sc.nextLine();
        }
    
        entrada = sc.nextLine();
    
        while(!entrada.equals("FIM")){
            PesquisaSequencial.verificaPesquisa(entrada);
            entrada = sc.nextLine();
        }
    
        sc.close();
    }
    
}


class Pokedex {
    //  private final String FILE_NAME = "C:\\Users\\kino1\\Desktop\\Programacao\\MyPrograms\\TPs\\TP2\\tmp\\pokemon.csv";
    private static final String FILE_NAME = "/tmp/pokemon.csv";
    public List<Pokemon> listaDePokemons = new ArrayList<>();

    public void lerDadosDoArquivo() {
        listaDePokemons.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            ler.readLine();  // Pula o cabeçalho
            while ((ln = ler.readLine()) != null) {
                double weight, height;
                String[] blocos = ln.split("\"");
                String[] atributo = blocos[0].split(",");
                String[] atributo2 = blocos[1].split(",");
                String[] atributo3 = blocos[2].split(",");

                if (atributo.length < 13000) { // Garante que todas as 11 colunas estão presentes
                    String id = atributo[0].trim();
                    int generation = Integer.parseInt(atributo[1].trim());
                    String name = atributo[2].trim();
                    String description = atributo[3].trim();

                    ArrayList<String> types = new ArrayList<>();
                    for (String type : atributo[4].split(",")) {
                        types.add(type.trim());
                    }

                    ArrayList<String> abilities = new ArrayList<>();
                    for (int i = 0; i < atributo2.length; i++) {
                        atributo2[i] = atributo2[i].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
                    }
                    for (String ability : atributo2) {
                        abilities.add(ability.trim());
                    }

                    weight = atributo3[1].equals("") ? 0 : Double.parseDouble(atributo3[1].trim());
                    height = atributo3[2].equals("") ? 0 : Double.parseDouble(atributo3[2].trim());
                    int captureRate = Integer.parseInt(atributo3[3].trim().replace("%", ""));

                    int isLegendaryInt = Integer.parseInt(atributo3[4].trim());
                    boolean isLegendary = isLegendaryInt == 1;

                    Date captureDate = null;

                    try {
                        captureDate = dateFormat.parse(atributo3[5].trim());
                    } catch (ParseException e) {
                        System.out.println("Erro ao parsear a data: " + atributo3[5].trim());
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
}

class Pokemon {
    private String id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private Date captureDate;

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

    // getter methods
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
        String formattedDate = dateFormat.format(captureDate);

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
