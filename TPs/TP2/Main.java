// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Scanner;

// class Pokedex {
//     private static final String FILE_NAME = "/tmp/pokemon.csv";
//     public List<Pokemon> listaDePokemons = new ArrayList<>();

//     public void lerDadosDoArquivo() {
//         listaDePokemons.clear();
//         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//         try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
//             String ln;
//             ler.readLine();  // Pula o cabeçalho
//             while ((ln = ler.readLine()) != null) {
//                 // Remove newline characters
//                 ln = ln.replace("\n", "").replace("\r", "");

//                 // Parse the line into a Pokemon object
//                 Pokemon pokemon = new Pokemon();

//                 // Split the line at double quotes
//                 String[] blocos = ln.split("\"");

//                 if (blocos.length < 3) {
//                     System.out.println("Linha mal formatada ou incompleta: " + ln);
//                     continue;
//                 }

//                 // Split blocos[0] at commas
//                 String[] atributo = blocos[0].split(",");
//                 for (int i = 0; i < atributo.length; i++) {
//                     atributo[i] = atributo[i].trim();
//                 }

//                 if (atributo.length < 6) {
//                     System.out.println("Linha mal formatada ou incompleta: " + ln);
//                     continue;
//                 }

//                 // Parse id
//                 String id = atributo[0];
//                 pokemon.setId(id);

//                 // Parse generation
//                 int generation = Integer.parseInt(atributo[1]);
//                 pokemon.setGeneration(generation);

//                 // Parse name
//                 String name = atributo[2];
//                 pokemon.setName(name);

//                 // Parse description
//                 String description = atributo[3];
//                 pokemon.setDescription(description);

//                 // Parse types
//                 if (!atributo[4].isEmpty()) {
//                     pokemon.addType(atributo[4]);
//                 }
//                 if (atributo.length > 5 && !atributo[5].isEmpty()) {
//                     pokemon.addType(atributo[5]);
//                 }

//                 // Now parse abilities from blocos[1], which is enclosed in quotes
//                 String abilitiesStr = blocos[1];

//                 // Remove '[' and ']' from abilitiesStr
//                 String abilitiesCleaned = abilitiesStr.replace("[", "").replace("]", "");

//                 // Split abilitiesCleaned at commas
//                 String[] abilitiesTokens = abilitiesCleaned.split(",");

//                 // Trim abilities tokens and remove leading/trailing single quotes
//                 for (String ability : abilitiesTokens) {
//                     ability = ability.trim();
//                     if (ability.startsWith("'") && ability.endsWith("'")) {
//                         ability = ability.substring(1, ability.length() - 1);
//                     }
//                     pokemon.addAbility(ability);
//                 }

//                 // Now process blocos[2], but since it starts with a comma, we need to handle that
//                 String resto = blocos[2];
//                 if (resto.startsWith(",")) {
//                     resto = resto.substring(1);
//                 }

//                 // Split resto at commas
//                 String[] atributo3 = resto.split(",");
//                 for (int i = 0; i < atributo3.length; i++) {
//                     atributo3[i] = atributo3[i].trim();
//                 }

//                 // Parse weight
//                 if (atributo3.length > 0 && !atributo3[0].isEmpty()) {
//                     double weight = Double.parseDouble(atributo3[0]);
//                     pokemon.setWeight(weight);
//                 }

//                 // Parse height
//                 if (atributo3.length > 1 && !atributo3[1].isEmpty()) {
//                     double height = Double.parseDouble(atributo3[1]);
//                     pokemon.setHeight(height);
//                 }

//                 // Parse captureRate
//                 if (atributo3.length > 2 && !atributo3[2].isEmpty()) {
//                     int captureRate = Integer.parseInt(atributo3[2]);
//                     pokemon.setCaptureRate(captureRate);
//                 }

//                 // Parse isLegendary
//                 if (atributo3.length > 3 && !atributo3[3].isEmpty()) {
//                     boolean isLegendary = atributo3[3].equals("1") || atributo3[3].equalsIgnoreCase("true");
//                     pokemon.setLegendary(isLegendary);
//                 }

//                 // Parse captureDate
//                 if (atributo3.length > 4 && !atributo3[4].isEmpty()) {
//                     try {
//                         Date captureDate = dateFormat.parse(atributo3[4]);
//                         pokemon.setCaptureDate(captureDate);
//                     } catch (Exception e) {
//                         System.out.println("Erro ao parsear a data: " + atributo3[4]);
//                     }
//                 }

//                 // Add the Pokemon to the list
//                 listaDePokemons.add(pokemon);
//             }
//         } catch (IOException x) {
//             x.printStackTrace();
//         }
//     }
// }

// class Pokemon {
//     public String id;
//     public int generation;
//     public String name;
//     public String description;
//     public List<String> types;
//     public List<String> abilities;
//     public double weight;
//     public double height;
//     public int captureRate;
//     public boolean isLegendary;
//     public Date captureDate;

//     public Pokemon() {
//         types = new ArrayList<>();
//         abilities = new ArrayList<>();
//     }

//     // Getter and Setter methods
//     public void setId(String id) {
//         this.id = id;
//     }

//     public void setGeneration(int generation) {
//         this.generation = generation;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public void addType(String type) {
//         this.types.add(type);
//     }

//     public void addAbility(String ability) {
//         this.abilities.add(ability);
//     }

//     public void setWeight(double weight) {
//         this.weight = weight;
//     }

//     public void setHeight(double height) {
//         this.height = height;
//     }

//     public void setCaptureRate(int captureRate) {
//         this.captureRate = captureRate;
//     }

//     public void setLegendary(boolean legendary) {
//         isLegendary = legendary;
//     }

//     public void setCaptureDate(Date captureDate) {
//         this.captureDate = captureDate;
//     }

//     public void imprimir() {
//         SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//         String formattedDate = dateFormat.format(captureDate);

//         System.out.print("[#" + id + " -> " + name + ": " + description + " - [");

//         // Print types
//         for (int i = 0; i < types.size(); i++) {
//             System.out.print("'" + types.get(i) + "'");
//             if (i < types.size() - 1) {
//                 System.out.print(", ");
//             }
//         }
//         System.out.print("] - [");

//         // Print abilities
//         for (int i = 0; i < abilities.size(); i++) {
//             System.out.print("'" + abilities.get(i) + "'");
//             if (i < abilities.size() - 1) {
//                 System.out.print(", ");
//             }
//         }
//         System.out.print("] - ");

//         System.out.printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
//                 weight, height, captureRate,
//                 isLegendary ? "true" : "false",
//                 generation, formattedDate);
//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         Pokedex pokedex = new Pokedex();
//         pokedex.lerDadosDoArquivo();
//         Scanner sc = new Scanner(System.in);

//         while (sc.hasNextLine()) {
//             String entrada = sc.nextLine().trim();

//             if (entrada.equals("FIM")) {
//                 break;
//             }

//             boolean pokemonEncontrado = false;

//             for (Pokemon pokemon : pokedex.listaDePokemons) {
//                 if (pokemon.id.equals(entrada)) {
//                     pokemonEncontrado = true;
//                     pokemon.imprimir();
//                     break;
//                 }
//             }

//             if (!pokemonEncontrado) {
//                 System.out.println("Pokemon não encontrado");
//             }
//         }

//         sc.close();
//     }
// }
