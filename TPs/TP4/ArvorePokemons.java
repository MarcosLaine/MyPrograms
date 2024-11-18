import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void imprimir() {
        System.out.println(name);
    }
}

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(Pokemon pokemon) {
        raiz = inserir(raiz, pokemon);
    }

    private No inserir(No no, Pokemon pokemon) {
        if (no == null) {
            return new No(pokemon);
        }
        if (pokemon.getId().compareTo(no.getPokemon().getId()) < 0) {
            no.esq = inserir(no.esq, pokemon);
        } else if (pokemon.getId().compareTo(no.getPokemon().getId()) > 0) {
            no.dir = inserir(no.dir, pokemon);
        }
        return no;
    }

    public void remover(String id) {
        raiz = remover(raiz, id);
    }

    private No remover(No no, String id) {
        if (no == null) {
            return null;
        }
        if (id.compareTo(no.getPokemon().getId()) < 0) {
            no.esq = remover(no.esq, id);
        } else if (id.compareTo(no.getPokemon().getId()) > 0) {
            no.dir = remover(no.dir, id);
        } else {
            if (no.esq == null) {
                return no.dir;
            } else if (no.dir == null) {
                return no.esq;
            } else {
                No sucessor = encontrarMinimo(no.dir);
                no.setPokemon(sucessor.getPokemon());
                no.dir = remover(no.dir, sucessor.getPokemon().getId());
            }
        }
        return no;
    }

    private No encontrarMinimo(No no) {
        while (no.esq != null) {
            no = no.esq;
        }
        return no;
    }

    public String pesquisar(String id) {
        StringBuilder caminho = new StringBuilder();
        boolean encontrado = pesquisar(raiz, id, caminho);
        return caminho.toString() + (encontrado ? " SIM" : " NAO");
    }

    private boolean pesquisar(No no, String id, StringBuilder caminho) {
        if (no == null) {
            return false;
        }
        if (id.equals(no.getPokemon().getId())) {
            no.getPokemon().imprimir();
            return true;
        } else if (id.compareTo(no.getPokemon().getId()) < 0) {
            caminho.append("esq ");
            return pesquisar(no.esq, id, caminho);
        } else {
            caminho.append("dir ");
            return pesquisar(no.dir, id, caminho);
        }
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    private void caminharCentral(No no) {
        if (no != null) {
            caminharCentral(no.esq);
            no.getPokemon().imprimir();
            caminharCentral(no.dir);
        }
    }
}

class No {
    private Pokemon pokemon;
    No esq, dir;

    public No(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.esq = null;
        this.dir = null;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}

class Pokedex {
    private final String FILE_NAME = "C:\\Users\\kino1\\Desktop\\Programacao\\MyPrograms\\TPs\\TP4\\tmp\\pokemon.csv";
    public ArvoreBinaria arvoreDePokemons = new ArvoreBinaria();
    private ArrayList<Pokemon> listaDePokemons = new ArrayList<>();

    public void lerDadosDoArquivo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            ler.readLine(); // Pula o cabeçalho
            while ((ln = ler.readLine()) != null) {
                String[] atributos = ln.split(",");
                if (atributos.length >= 11) {
                    String id = atributos[0].trim();
                    int generation = Integer.parseInt(atributos[1].trim());
                    String name = atributos[2].trim();
                    String description = atributos[3].trim();

                    ArrayList<String> types = new ArrayList<>();
                    for (String type : atributos[4].split(";")) {
                        types.add(type.trim());
                    }

                    ArrayList<String> abilities = new ArrayList<>();
                    for (String ability : atributos[5].split(";")) {
                        abilities.add(ability.trim());
                    }

                    double weight = Double.parseDouble(atributos[6].trim());
                    double height = Double.parseDouble(atributos[7].trim());
                    int captureRate = Integer.parseInt(atributos[8].trim());
                    boolean isLegendary = Integer.parseInt(atributos[9].trim()) == 1;

                    Date captureDate = null;
                    try {
                        captureDate = dateFormat.parse(atributos[10].trim());
                    } catch (ParseException e) {
                        System.out.println("Erro ao parsear a data: " + atributos[10].trim());
                    }

                    Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight, height, captureRate, isLegendary, captureDate);
                    listaDePokemons.add(pokemon);
                } else {
                    System.out.println("Linha mal formatada ou incompleta: " + ln);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pokemon getPokemonById(String id) {
        for (Pokemon pokemon : listaDePokemons) {
            if (pokemon.getId().equals(id)) {
                return pokemon;
            }
        }
        return null;
    }
}

public class ArvorePokemons {
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        pokedex.lerDadosDoArquivo();
        Scanner sc = new Scanner(System.in);

        // Inserção de Pokémons na árvore
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            Pokemon pokemon = pokedex.getPokemonById(entrada);
            if (pokemon != null) {
                pokedex.arvoreDePokemons.inserir(pokemon);
            } else {
                System.out.println("Pokemon com ID " + entrada + " não encontrado no arquivo.");
            }
            entrada = sc.nextLine();
        }

        // Pesquisa de Pokémons na árvore
        entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            String resultado = pokedex.arvoreDePokemons.pesquisar(entrada);
            System.out.println(resultado);
            entrada = sc.nextLine();
        }

        // Caminhada central (em ordem)
        System.out.println("Caminhada Central:");
        pokedex.arvoreDePokemons.caminharCentral();

        sc.close();
    }
}
