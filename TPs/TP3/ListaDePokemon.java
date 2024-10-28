import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Lista de Pokemons, baseada em uma lista de inteiros.
 */
public class ListaDePokemon {
    private ArrayList<Pokemon> lista;

    public ListaDePokemon() {
        lista = new ArrayList<>();
    }

    public void inserirInicio(Pokemon pokemon) {
        lista.add(0, pokemon);
    }

    public void inserir(Pokemon pokemon, int pos) throws Exception {
        if (pos < 0 || pos > lista.size()) {
            throw new Exception("Erro ao inserir: posição inválida!");
        }
        lista.add(pos, pokemon);
    }

    public void inserirFim(Pokemon pokemon) {
        lista.add(pokemon);
    }

    public Pokemon removerInicio() throws Exception {
        if (lista.isEmpty()) {
            throw new Exception("Erro ao remover: lista vazia!");
        }
        return lista.remove(0);
    }

    public Pokemon removerFim() throws Exception {
        if (lista.isEmpty()) {
            throw new Exception("Erro ao remover: lista vazia!");
        }
        return lista.remove(lista.size() - 1);
    }

    public Pokemon remover(int pos) throws Exception {
        if (lista.isEmpty() || pos < 0 || pos >= lista.size()) {
            throw new Exception("Erro ao remover: posição inválida ou lista vazia!");
        }
        return lista.remove(pos);
    }

    public void mostrar() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print("[" + i + "] ");
            lista.get(i).imprimir();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDePokemon listaDePokemon = new ListaDePokemon();
        Pokedex pokedex = new Pokedex();
        pokedex.lerDadosDoArquivo();

        // Leitura dos IDs de Pokémon
        while (true) {
            String id = sc.nextLine().trim();
            if (id.equalsIgnoreCase("FIM")) {
                break;
            }
            Pokemon pokemon = buscarPokemonPorId(pokedex, id);
            if (pokemon != null) {
                listaDePokemon.inserirFim(pokemon);
            }
        }

        // Ler e processar comandos
        int n = sc.nextInt();
        sc.nextLine(); // Consumir a linha restante

        try {
            for (int i = 0; i < n; i++) {
                String comando = sc.nextLine();
                String[] partes = comando.split(" ");
                String operacao = partes[0];

                switch (operacao) {
                    case "II": // Inserir no início
                        String idPokemonII = partes[1];
                        Pokemon pokemonII = buscarPokemonPorId(pokedex, idPokemonII);
                        if (pokemonII != null) {
                            listaDePokemon.inserirInicio(pokemonII);
                        }
                        break;
                    case "IF": // Inserir no fim
                        String idPokemonIF = partes[1];
                        Pokemon pokemonIF = buscarPokemonPorId(pokedex, idPokemonIF);
                        if (pokemonIF != null) {
                            listaDePokemon.inserirFim(pokemonIF);
                        }
                        break;
                    case "I*": // Inserir em posição específica
                        int posicaoI = Integer.parseInt(partes[1]);
                        String idPokemonI = partes[2];
                        Pokemon pokemonI = buscarPokemonPorId(pokedex, idPokemonI);
                        if (pokemonI != null) {
                            listaDePokemon.inserir(pokemonI, posicaoI);
                        }
                        break;
                    case "RI": // Remover do início
                        Pokemon removidoRI = listaDePokemon.removerInicio();
                        System.out.println("(R) " + removidoRI.getName());
                        break;
                    case "RF": // Remover do fim
                        Pokemon removidoRF = listaDePokemon.removerFim();
                        System.out.println("(R) " + removidoRF.getName());
                        break;
                    case "R*": // Remover de posição específica
                        int posicaoR = Integer.parseInt(partes[1]);
                        Pokemon removidoR = listaDePokemon.remover(posicaoR);
                        System.out.println("(R) " + removidoR.getName());
                        break;
                    default:
                        System.out.println("Comando inválido: " + operacao);
                        break;
                }
            }

            listaDePokemon.mostrar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static Pokemon buscarPokemonPorId(Pokedex pokedex, String id) {
        for (Pokemon pokemon : pokedex.listaDePokemons) {
            if (pokemon.getId().equalsIgnoreCase(id)) {
                return pokemon;
            }
        }
        System.out.println("Pokemon não encontrado: " + id);
        return null;
    }

    private static Pokemon buscarPokemonPorNome(Pokedex pokedex, String nome) {
        for (Pokemon pokemon : pokedex.listaDePokemons) {
            if (pokemon.getName().equalsIgnoreCase(nome)) {
                return pokemon;
            }
        }
        System.out.println("Pokemon não encontrado: " + nome);
        return null;
    }
}

class Pokedex {
    // private final String FILE_NAME = "/home/marcoslaine/Área de
    // trabalho/Programacao/MyPrograms/TPs/TP3/tmp/pokemon.csv";
    private static final String FILE_NAME = "/tmp/pokemon.csv";
    public List<Pokemon> listaDePokemons = new ArrayList<>();

    public void lerDadosDoArquivo() {
        listaDePokemons.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            ler.readLine(); // Pula o cabeçalho
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

                    Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight, height,
                            captureRate, isLegendary, captureDate);
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

    public Pokemon(String id, int generation, String name, String description, ArrayList<String> types,
            ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary,
            Date captureDate) {
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void imprimir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(captureDate);

        System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

        for (int i = 0; i < types.size(); i++) {
            System.out.print("'" + types.get(i) + "'");
            if (i < types.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] - [");

        for (int i = 0; i < abilities.size(); i++) {
            System.out.print(abilities.get(i));
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
