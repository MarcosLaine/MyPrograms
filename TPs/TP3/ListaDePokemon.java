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

    /**
     * Construtor da classe.
     */
    public ListaDePokemon() {
        lista = new ArrayList<>();
    }

    /**
     * Insere um Pokemon na primeira posição da lista.
     * @param pokemon Pokemon a ser inserido.
     */
    public void inserirInicio(Pokemon pokemon) {
        lista.add(0, pokemon);
    }

    /**
     * Insere um Pokemon em uma posição específica da lista.
     * @param pokemon Pokemon a ser inserido.
     * @param pos Posição de inserção.
     * @throws Exception Se a posição for inválida.
     */
    public void inserir(Pokemon pokemon, int pos) throws Exception {
        if (pos < 0 || pos > lista.size()) {
            throw new Exception("Erro ao inserir: posição inválida!");
        }
        lista.add(pos, pokemon);
    }

    /**
     * Insere um Pokemon na última posição da lista.
     * @param pokemon Pokemon a ser inserido.
     */
    public void inserirFim(Pokemon pokemon) {
        lista.add(pokemon);
    }

    /**
     * Remove e retorna o Pokemon da primeira posição da lista.
     * @return Pokemon removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Pokemon removerInicio() throws Exception {
        if (lista.isEmpty()) {
            throw new Exception("Erro ao remover: lista vazia!");
        }
        return lista.remove(0);
    }

    /**
     * Remove e retorna o Pokemon da última posição da lista.
     * @return Pokemon removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Pokemon removerFim() throws Exception {
        if (lista.isEmpty()) {
            throw new Exception("Erro ao remover: lista vazia!");
        }
        return lista.remove(lista.size() - 1);
    }

    /**
     * Remove e retorna um Pokemon de uma posição específica da lista.
     * @param pos Posição de remoção.
     * @return Pokemon removido.
     * @throws Exception Se a lista estiver vazia ou a posição for inválida.
     */
    public Pokemon remover(int pos) throws Exception {
        if (lista.isEmpty() || pos < 0 || pos >= lista.size()) {
            throw new Exception("Erro ao remover: posição inválida ou lista vazia!");
        }
        return lista.remove(pos);
    }

    /**
     * Mostra os Pokemons cadastrados na lista.
     */
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
                        String nomePokemonII = partes[1];
                        Pokemon pokemonII = buscarPokemonPorNome(pokedex, nomePokemonII);
                        if (pokemonII != null) {
                            listaDePokemon.inserirInicio(pokemonII);
                        }
                        break;
                    case "IF": // Inserir no fim
                        String nomePokemonIF = partes[1];
                        Pokemon pokemonIF = buscarPokemonPorNome(pokedex, nomePokemonIF);
                        if (pokemonIF != null) {
                            listaDePokemon.inserirFim(pokemonIF);
                        }
                        break;
                    case "I*": // Inserir em posição específica
                        String nomePokemonI = partes[1];
                        int posicaoI = Integer.parseInt(partes[2]);
                        Pokemon pokemonI = buscarPokemonPorNome(pokedex, nomePokemonI);
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

    /**
     * Busca um Pokemon pelo nome na Pokedex.
     * @param pokedex Pokedex contendo todos os Pokemons.
     * @param nome Nome do Pokemon a ser buscado.
     * @return Pokemon encontrado ou null se não encontrado.
     */
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
    // private final String FILE_NAME = "C:\\Users\\kino1\\Desktop\\Programacao\\MyPrograms\\TPs\\TP2\\tmp\\pokemon.csv";
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

    public Pokemon(String id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
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

    // Métodos de acesso (getters e setters)
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

        // Imprime os tipos
        for (int i = 0; i < types.size(); i++) {
            System.out.print("'" + types.get(i) + "'");
            if (i < types.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] - [");

        // Imprime as habilidades
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
