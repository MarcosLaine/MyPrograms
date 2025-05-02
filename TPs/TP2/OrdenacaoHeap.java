import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
class OrdenacaoHeap {
    private String id,name, description;
    private int generation, captureRate;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight, height;
    private boolean isLegendary;
    private Date captureDate;
  // Construtor padrão
  public OrdenacaoHeap() {
    this.types = new ArrayList<>();
    this.abilities = new ArrayList<>();
    this.captureDate =captureDate;
}
    public OrdenacaoHeap(String id, int generation, String name, String description, ArrayList<String> types,
    ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary,Date captureDate) {
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

// Métodos getters
public String getId() { return id; }
public String getName() { return name; }
public double getHeight() {return height;}

    public void imprimir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String novaData = dateFormat.format(captureDate);
//para exibir na formatacao
        System.out.print("[#" + id + " -> " + name + ": " + description + " - [");
        for (int i = 0; i < types.size(); i++) 
        { System.out.print("'" + types.get(i) + "'");
            if (i < types.size() - 1) 
            {System.out.print(", ");
            }
        }
        System.out.print("] - [");
        for (int i = 0; i < abilities.size(); i++) 
        {
            System.out.print("" + abilities.get(i) + "");
            if (i < abilities.size() - 1) 
            {System.out.print(", ");
            }
        }System.out.print("] - ");

        System.out.printf("%.1fkg - %.1fm - %d%% - %s - %d gen] - %s\n",
        weight, height, captureRate,isLegendary ? "true" : "false",
        generation, novaData);
    }
    public int compareTo(OrdenacaoHeap other) {
        if (this.height != other.height) {
            return Double.compare(this.height, other.height);
        }
        return this.name.compareTo(other.name); // Comparação pelo nome em caso de empate
    }
    
    public static void main(String[] args) {
        BancoPok bancoPok = new BancoPok();
        Scanner sc = new Scanner(System.in);

        Set<String> idsSolicitados = new HashSet<>();
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            idsSolicitados.add(entrada.trim());
            entrada = sc.nextLine();
        }

       bancoPok.readFilePokemon(idsSolicitados);

        long startTime = System.currentTimeMillis();
        int comparacoes = 0;
        int movimentacoes = 0;
        List<OrdenacaoHeap> lista =bancoPok.totalPok;
        int n = lista.size();

        for (int i = n / 2 - 1; i >= 0; i--) 
        { estruturaHeap(lista, n, i);
        }

        for (int i = n - 1; i > 0; i--) 
        {
            OrdenacaoHeap aux = lista.get(0);
            lista.set(0, lista.get(i));
            lista.set(i, aux);
            movimentacoes++;
            estruturaHeap(lista, i, 0);
        }

        long endTime = System.currentTimeMillis();
        long tempoExecucao = endTime - startTime;

        for (OrdenacaoHeap p : lista)
         { p.imprimir();
        }

        String matricula = "813225";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(matricula + "_heap.txt"))) {
            writer.write(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoExecucao);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }sc.close();
    }

    public static void estruturaHeap(List<OrdenacaoHeap> lista, int n, int i) {
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int maior = i;
    
        if (esq < n && lista.get(esq).compareTo(lista.get(maior)) > 0) {
            maior = esq;
        }
    
        if (dir < n && lista.get(dir).compareTo(lista.get(maior)) > 0) {
            maior = dir;
        }
    
        if (maior != i) {
            OrdenacaoHeap troca = lista.get(i);
            lista.set(i, lista.get(maior));
            lista.set(maior, troca);
            estruturaHeap(lista, n, maior);
        }
    }
}    
class BancoPok {
    private final String ARQ = "/tmp/pokemon.csv";
    public List<OrdenacaoHeap> totalPok = new ArrayList<>();
    public void readFilePokemon(Set<String> arrID) {
        totalPok.clear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader ler = new BufferedReader(new FileReader(ARQ))) {
            String ln;
            ler.readLine(); // Pula o cabeçalho
            while ((ln = ler.readLine()) != null) 
            {double weight, height;
                String[] blocos = ln.split("\"");
                String[] atributo = blocos[0].split(",");

                if (atributo.length < 13000) 
                {String id = atributo[0].trim();
                    if (arrID.contains(id)) 
                    {
                        int generation = Integer.parseInt(atributo[1].trim());
                        String name = atributo[2].trim();
                        String description = atributo[3].trim();

                        ArrayList<String> types = new ArrayList<>();
                        types.add(atributo[4].trim()); // type1
                        if (atributo.length > 5 && !atributo[5].trim().isEmpty()) 
                        {types.add(atributo[5].trim());
                        }

                        ArrayList<String> abilities = new ArrayList<>();
                        String[] atributo2 = blocos[1].split(",");
                        for (int i = 0; i < atributo2.length; i++) 
                        {atributo2[i] = atributo2[i].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"",
                         "");
                        }
                        for (String ability : atributo2) 
                        {abilities.add(ability.trim());
                        }

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
                        }
                        OrdenacaoHeap pokemon = new OrdenacaoHeap(id, generation, name, description, types, abilities, weight,
                        height,captureRate, isLegendary, captureDate);
                        totalPok.add(pokemon);
                    }
                } 
            }
        } catch (IOException x) {x.printStackTrace();}
    }
}