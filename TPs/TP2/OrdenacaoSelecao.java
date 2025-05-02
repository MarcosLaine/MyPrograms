
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Pokedex {
    private final String FILE_NAME = "/tmp/pokemon.csv";
    public List<Pokemon> listaDePokemons = new ArrayList<>();

    public void lerDadosDoArquivo(Set<String> idsSolicitados) {
        listaDePokemons.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            ler.readLine(); // Pula o cabeçalho
            while ((ln = ler.readLine()) != null) {
                double weight, height;
                String[] blocos = ln.split("\"");
                String[] atributo = blocos[0].split(",");

                if (atributo.length < 13000) {
                    String id = atributo[0].trim();

                    // verifica ids
                    if (idsSolicitados.contains(id)) {
                        int generation = Integer.parseInt(atributo[1].trim());
                        String name = atributo[2].trim();
                        String description = atributo[3].trim();

                        // Lê tipos
                        ArrayList<String> types = new ArrayList<>();
                        types.add(atributo[4].trim()); // type1
                        if (atributo.length > 5 && !atributo[5].trim().isEmpty()) {
                            types.add(atributo[5].trim());
                        }

                        // Lê as habilidades
                        ArrayList<String> abilities = new ArrayList<>();
                        String[] atributo2 = blocos[1].split(",");
                        for (int i = 0; i < atributo2.length; i++) {
                            atributo2[i] = atributo2[i].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"",
                                    "");
                        }
                        for (String ability : atributo2) {
                            abilities.add(ability.trim());
                        }

                        // Lê peso e altura
                        String[] atributo3 = blocos[2].split(",");
                        weight = atributo3[1].equals("") ? 0 : Double.parseDouble(atributo3[1].trim());
                        height = atributo3[2].equals("") ? 0 : Double.parseDouble(atributo3[2].trim());
                        int captureRate = Integer.parseInt(atributo3[3].trim().replace("%", ""));

                        int isLegendaryInt = Integer.parseInt(atributo3[4].trim());
                        boolean isLegendary = isLegendaryInt == 1;

                        Date captureDate = null;
                        try {
                            captureDate = dateFormat.parse(atributo3[5].trim());
                        } catch (Exception e) {
                            System.out.println("Erro ao parsear a data: " + atributo3[5].trim());
                        }

                        Pokemon pokemon = new Pokemon(id, generation, name, description, types, abilities, weight,
                                height,
                                captureRate, isLegendary, captureDate);
                        listaDePokemons.add(pokemon);
                    }
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

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
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

public class OrdenacaoSelecao {

    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        Scanner sc = new Scanner(System.in);

        Set<String> idsSolicitados = new HashSet<>();
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            idsSolicitados.add(entrada.trim());
            entrada = sc.nextLine();
        }

        pokedex.lerDadosDoArquivo(idsSolicitados);

        long startTime = System.currentTimeMillis();
        int comparacoes = 0;
        int movimentacoes = 0;

        List<Pokemon> lista = pokedex.listaDePokemons;
        for (int i = 0; i < lista.size() - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < lista.size(); j++) {
                comparacoes++;
                if (lista.get(j).getName().compareTo(lista.get(menor).getName()) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                Pokemon temp = lista.get(i);
                lista.set(i, lista.get(menor));
                lista.set(menor, temp);
                movimentacoes++;
            }
        }

        long endTime = System.currentTimeMillis();
        long tempoExecucao = endTime - startTime;

        for (Pokemon p : lista) {
            p.imprimir();
        }

        String matricula = "803627";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(matricula + "_selecao.txt"))) {
            writer.write(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }

        sc.close();
    }
}
