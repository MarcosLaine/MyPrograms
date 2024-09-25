
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Pokemon {

    private final String id;
    private final int generation;
    private final String name;
    private final String description;
    private final ArrayList types;
    private final ArrayList abilities;
    private final double weight;
    private final double height;
    private final int captureRate;
    private final boolean isLegendary;
    private final Date captureDate;

    private static final List<Pokemon> listaDePokemons = new ArrayList<>();

    private static final String FILE_NAME = "/tmp/pokemon.csv";

    public Pokemon(String id, int generation, String name, String description, ArrayList types, ArrayList abilities, double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
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

    public void imprimir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(captureDate);

        System.out.println(
                "[#" + id + " → " + name + ": " + description + " - "
                + types + " - " + abilities + " - "
                + weight + "kg - " + height + "m - "
                + captureRate + "% - " + isLegendary + " - "
                + generation + " gen" + " - " + formattedDate + "]"
        );
    }

    public static void lerDadosDoArquivo() {
        listaDePokemons.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) {
                String[] atributo = ln.split(";");
                if (atributo.length == 11) { // Garante que todas as 11 colunas estão presentes
                    String id = (atributo[0].trim());
                    int generation = Integer.parseInt(atributo[1].trim());
                    String name = atributo[2].trim();
                    String description = atributo[3].trim();
                    ArrayList<String> types = new ArrayList<>();
                    for (String type : atributo[4].split(",")) {
                        types.add(type.trim());
                    }
                    ArrayList<String> abilities = new ArrayList<>();
                    for (String ability : atributo[5].split(",")) {
                        abilities.add(ability.trim());
                    }
                    double weight = Double.parseDouble(atributo[6].trim());
                    double height = Double.parseDouble(atributo[7].trim());
                    int captureRate = Integer.parseInt(atributo[8].trim().replace("%", ""));
                    boolean isLegendary = Boolean.parseBoolean(atributo[9].trim());
                    Date captureDate = null;
                    try {
                        captureDate = dateFormat.parse(atributo[10].trim());
                    } catch (ParseException e) {
                        System.out.println("Erro ao parsear a data: " + atributo[10].trim());
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

    public static void main(String[] args) {
        lerDadosDoArquivo();
        String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")){
            String idEntrada = entrada;
            boolean pokemonEncontrado = false;

            for(Pokemon pokemon : listaDePokemons){
                if(pokemon.id.equals(idEntrada)){
                    pokemonEncontrado = true;
                    pokemon.imprimir();
                    break;
                }
            }

            if (!pokemonEncontrado) {
                System.out.println("pokemon não encontrado");
            }

        }
    }
    
    

}
