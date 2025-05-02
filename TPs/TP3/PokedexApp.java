import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

class Pokemon {
    String id;
    int generation;
    String name;
    String description;
    List<String> types;
    List<String> abilities;
    double weight;
    double height;
    int captureRate;
    boolean isLegendary;
    Date captureDate;

    public Pokemon() {
        types = new ArrayList<>();
        abilities = new ArrayList<>();
    }
}

class Stack {
    private List<Pokemon> stack;
    private int capacity;

    public Stack(int initialCapacity) {
        this.capacity = initialCapacity;
        this.stack = new ArrayList<>(initialCapacity);
    }

    public void push(Pokemon pokemon) {
        if (stack.size() >= capacity) {
            capacity *= 2;
            ((ArrayList<Pokemon>) stack).ensureCapacity(capacity);
        }
        stack.add(pokemon);
    }

    public Pokemon pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    public Pokemon get(int index) {
        return stack.get(index);
    }

    public int size() {
        return stack.size();
    }

    public List<Pokemon> getStack() {
        return stack;
    }
}

public class PokedexApp {

    private static String trim(String str) {
        return str.trim();
    }

    private static List<String> splitAtChar(String str, char delimiter) {
        List<String> tokens = new ArrayList<>();
        String[] parts = str.split(String.valueOf(delimiter));
        Collections.addAll(tokens, parts);
        return tokens;
    }

    private static void lerDadosDoArquivo(Stack stack) {
        String FILE_NAME = "/tmp/pokemon.csv";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            br.readLine(); // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                line = line.replace("\r", "").replace("\n", "");

                String[] blocos = line.split("\"");
                if (blocos.length < 3) {
                    System.out.println("Linha mal formatada ou incompleta: " + line);
                    continue;
                }

                String[] atributos = blocos[0].split(",");
                for (int i = 0; i < atributos.length; i++) {
                    atributos[i] = trim(atributos[i]);
                }

                if (atributos.length < 6) {
                    System.out.println("Linha mal formatada ou incompleta: " + line);
                    continue;
                }

                Pokemon pokemon = new Pokemon();
                pokemon.id = atributos[0];
                pokemon.generation = Integer.parseInt(atributos[1]);
                pokemon.name = atributos[2];
                pokemon.description = atributos[3];

                if (!atributos[4].isEmpty()) {
                    pokemon.types.add(trim(atributos[4]));
                }
                if (atributos.length > 5 && !atributos[5].isEmpty()) {
                    pokemon.types.add(trim(atributos[5]));
                }

                String abilitiesStr = blocos[1].replace("[", "").replace("]", "");
                String[] abilitiesTokens = abilitiesStr.split(",");
                for (String ability : abilitiesTokens) {
                    pokemon.abilities.add(trim(ability));
                }

                String[] atributo3 = blocos[2].split(",");
                for (int i = 0; i < atributo3.length; i++) {
                    atributo3[i] = trim(atributo3[i]);
                }

                if (atributo3.length > 0) {
                    pokemon.weight = Double.parseDouble(atributo3[0]);
                }
                if (atributo3.length > 1) {
                    pokemon.height = Double.parseDouble(atributo3[1]);
                }
                if (atributo3.length > 2) {
                    pokemon.captureRate = Integer.parseInt(atributo3[2]);
                }
                if (atributo3.length > 3) {
                    pokemon.isLegendary = Integer.parseInt(atributo3[3]) == 1;
                }
                if (atributo3.length > 4) {
                    try {
                        pokemon.captureDate = dateFormat.parse(atributo3[4]);
                    } catch (ParseException e) {
                        System.out.println("Erro ao parsear a data: " + atributo3[4]);
                    }
                }

                stack.push(pokemon);
            }
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void imprimirPokemon(Pokemon pokemon, int index) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = pokemon.captureDate != null ? dateFormat.format(pokemon.captureDate) : "N/A";

        System.out.printf("[%d] [#%s -> %s: %s - [", index, pokemon.id, pokemon.name, pokemon.description);
        for (int i = 0; i < pokemon.types.size(); i++) {
            System.out.printf("'%s'", pokemon.types.get(i));
            if (i < pokemon.types.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] - [");
        for (int i = 0; i < pokemon.abilities.size(); i++) {
            System.out.print(pokemon.abilities.get(i));
            if (i < pokemon.abilities.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
                pokemon.weight, pokemon.height, pokemon.captureRate,
                pokemon.isLegendary ? "true" : "false",
                pokemon.generation, dateStr);
    }

    private static boolean encontrarPokemonPorId(Stack stack, String id, Pokemon[] resultado) {
        for (Pokemon pokemon : stack.getStack()) {
            if (pokemon.id.equals(id)) {
                resultado[0] = pokemon;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Stack pokedexCompleto = new Stack(1000);
        lerDadosDoArquivo(pokedexCompleto);

        Stack pokedexUsuario = new Stack(100);

        Scanner scanner = new Scanner(System.in);

        // Leitura de IDs de Pokémon do usuário
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equals("FIM")) {
                break;
            }

            Pokemon[] pokemonEncontrado = new Pokemon[1];
            if (encontrarPokemonPorId(pokedexCompleto, entrada, pokemonEncontrado)) {
                pokedexUsuario.push(pokemonEncontrado[0]);
            } else {
                System.out.println("Pokemon com ID " + entrada + " não encontrado");
            }
        }

        int numComandos = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha restante

        // Processar comandos de manipulação
        while (numComandos > 0) {
            String entrada = scanner.nextLine();
            String[] partes = entrada.split(" ");
            String comando = partes[0];

            if (comando.equals("I")) { // Inserir
                Pokemon[] pokemon = new Pokemon[1];
                if (encontrarPokemonPorId(pokedexCompleto, partes[1], pokemon)) {
                    pokedexUsuario.push(pokemon[0]);
                }
            } else if (comando.equals("R")) { // Remover
                Pokemon removido = pokedexUsuario.pop();
                System.out.println("(R) " + removido.name);
            } else {
                System.out.println("Comando inválido: " + comando);
            }
            numComandos--;
        }

        // Mostrar a Pokedex do usuário com índices
        for (int i = 0; i < pokedexUsuario.size(); i++) {
            imprimirPokemon(pokedexUsuario.get(i), i);
        }
    }
    
}
