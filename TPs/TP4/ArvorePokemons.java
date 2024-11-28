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
        System.out.println(name);
    }

    public int getIntId() {
        return Integer.parseInt(id);
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
        if (pokemon.getIntId() < no.getPokemon().getIntId()) {
            no.esq = inserir(no.esq, pokemon);
        } else if (pokemon.getIntId() > no.getPokemon().getIntId()) {
            no.dir = inserir(no.dir, pokemon);
        }
        return no;
    }
    

    public void remover(Pokemon pokemon) {
        raiz = remover(raiz, pokemon);
    }

    private No remover(No no, Pokemon pokemon) {
        if (no == null) {
            return null;
        }
        if (pokemon.getIntId() < no.getPokemon().getIntId()) {
            no.esq = remover(no.esq, pokemon);
        } else if (pokemon.getIntId() > no.getPokemon().getIntId()) {
            no.dir = remover(no.dir, pokemon);
        } else {
            if (no.esq == null) {
                return no.dir;
            } else if (no.dir == null) {
                return no.esq;
            } else {
                No sucessor = encontrarMinimo(no.dir);
                no.setPokemon(sucessor.getPokemon());
                no.dir = remover(no.dir, sucessor.getPokemon());
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

    public String pesquisarPorNome(String id) {
        StringBuilder caminho = new StringBuilder("=>raiz ");
        boolean encontrado = pesquisarPorNome(raiz, id, caminho);
        return caminho.toString() + (encontrado ? "SIM" : "NAO");
    }

    private boolean pesquisarPorNome(No no, String id, StringBuilder caminho) {
        int idInt = Integer.parseInt(id);
        if (no == null) {
            System.out.println(id);
            return false; // Não encontrado
        }
        
        else if (id.equals(no.getPokemon().getId())) {
            no.getPokemon().imprimir();
            return true; // Encontrado
        }
        
        else if (idInt < no.getPokemon().getIntId()) {
            caminho.append("esq ");
            if (pesquisarPorNome(no.esq, id, caminho)) {
                return true; // Propaga o sucesso
            }
        } else if(idInt > no.getPokemon().getIntId()) {
            caminho.append("dir ");
            if (pesquisarPorNome(no.dir, id, caminho)) {
                return true; // Propaga o sucesso
            }
        }
        
        return false; // Não encontrado
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

    public void getMaior() {
        No no = raiz;
        if (no == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        while (no.dir != null) {
            no = no.dir; // Percorre até o nó mais à direita
        }
        System.out.println("Maior Pokémon com base no id: " + no.getPokemon().getName() + " - " + no.getPokemon().getId());
    }

    public No getRaiz() {
        return raiz;
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
    // private final String FILE_NAME = "C:\\Users\\kino1\\Desktop\\Programacao\\MyPrograms\\TPs\\TP4\\tmp\\pokemon.csv"; // my directory in windows
    // private final String FILE_NAME = "/home/marcoslaine/Área de trabalho/Programacao/MyPrograms/TPs/TP4/tmp/pokemon.csv"; // my directory in linux
    private final String FILE_NAME = "/tmp/pokemon.csv"; // Verde's directory
    public ArvoreBinaria arvoreDePokemons = new ArvoreBinaria();
    public ArrayList<Pokemon> listaDePokemons = new ArrayList<>();

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

        // pokedex.arvoreDePokemons.getMaior();


        // Pesquisa de Pokémons na árvore
        entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            String id = "";
            for(Pokemon pokemonBuscado : pokedex.listaDePokemons){
                if(pokemonBuscado.getName().equals(entrada)){
                    id = pokemonBuscado.getId();
                    break;
                }
            }
            String resultado = pokedex.arvoreDePokemons.pesquisarPorNome(id);
            System.out.println(resultado);
            entrada = sc.nextLine();
        }

        // Caminhada central (em ordem)
        // System.out.println("Caminhada Central:");
        // pokedex.arvoreDePokemons.caminharCentral();

        sc.close();
    }
}
