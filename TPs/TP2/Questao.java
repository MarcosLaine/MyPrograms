import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Personagem {
    private String id;
    String name;
    List<String> alternate_names;
    String house;
    String ancestry;
    String species;
    String patronus;
    boolean hogwartsStaff;
    boolean hogwartsStudent;
    String actorName;
    boolean alive;
    LocalDate dateOfBirth;
    int yearOfBirth;
    String eyeColour;
    String gender;
    String hairColour;
    boolean Wizard;

    public Personagem(String id, String name, List<String> alternate_names, String house, String ancestry,
            String species, String patronus,
            String hogwartsStaff, String hogwartsStudent, String actorName, String alive, LocalDate dateOfBirth,
            int yearOfBirth, String eyeColour, String gender, String hairColour, String Wizard) {
        if (id != null)
            this.id = id;
        if (name != null)
            this.name = name;
        if (alternate_names != null)
            this.alternate_names = alternate_names;
        if (house != null)
            this.house = house;
        if (ancestry != null)
            this.ancestry = ancestry;
        if (species != null)
            this.species = species;
        if (patronus != null)
            this.patronus = patronus;
        if (hogwartsStaff.charAt(0) == 'V')
            this.hogwartsStaff = true;
        if (hogwartsStudent.charAt(0) == 'V')
            this.hogwartsStudent = true;
        if (actorName != null)
            this.actorName = actorName;
        if (alive.charAt(0) == 'V')
            this.alive = true;
        if (dateOfBirth != null)
            this.dateOfBirth = dateOfBirth;
        if (yearOfBirth != 0)
            this.yearOfBirth = yearOfBirth;
        if (eyeColour != null)
            this.eyeColour = eyeColour;
        if (gender != null)
            this.gender = gender;
        if (hairColour != null)
            this.hairColour = hairColour;
        if (Wizard.charAt(0) == 'V')
            this.Wizard = true;
    }

    public Personagem() {
        this.id = "";
        this.name = "";
        this.alternate_names = new ArrayList<>();
        this.house = "";
        this.ancestry = "";
        this.species = "";
        this.patronus = "";
        this.hogwartsStaff = false;
        this.hogwartsStudent = false;
        this.actorName = "";
        this.alive = false;
        this.dateOfBirth = null;
        this.yearOfBirth = Integer.MIN_VALUE;// isso aki pode dar ruim
        this.eyeColour = "";
        this.gender = "";
        this.hairColour = "";
        this.Wizard = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(List<String> alternate_names) {
        this.alternate_names = alternate_names;
    }

    public void addAlternate_names(String alternateName) {
        if (alternate_names == null) {
            alternate_names = new ArrayList<>();
        }
        alternate_names.add(alternateName);
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(Boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(Boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public boolean isWizard() {
        return Wizard;
    }

    public void setWizard(boolean wizard) {
        Wizard = wizard;
    }

    public Personagem clonePersonagem() {
        Personagem clone = new Personagem();
        clone.id = this.id;
        clone.name = this.name;
        clone.alternate_names = new ArrayList<>(this.alternate_names);
        clone.house = this.house;
        clone.ancestry = this.ancestry;
        clone.species = this.species;
        clone.patronus = this.patronus;
        clone.hogwartsStaff = this.hogwartsStaff;
        clone.hogwartsStudent = this.hogwartsStudent;
        clone.actorName = this.actorName;
        clone.alive = this.alive;
        clone.dateOfBirth = this.dateOfBirth;
        clone.yearOfBirth = this.yearOfBirth;
        clone.eyeColour = this.eyeColour;
        clone.gender = this.gender;
        clone.hairColour = this.hairColour;
        clone.Wizard = this.Wizard;
        return clone;
    }

    public List<Personagem> ler(String path) throws IOException {

        List<Personagem> todosPersonagens = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linha;

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String[] separado;
            Personagem aSerAdicionado = new Personagem();
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                separado = linha.split(";");
                // se a funcao replace ela passa por toda a linha era melhor ter feito apenas um
                // for
                // porque 3 replace eh 3n e um for era apenas n
                separado[2] = separado[2].replace("[", "")
                        .replace("]", "")
                        .replace("'", "");
                if (separado[12].equals("23-6-1980"))
                    separado[12] = "23-06-1980";
                aSerAdicionado = new Personagem(
                        separado[0],
                        separado[1],
                        Arrays.asList(separado[2].split(",")),
                        separado[3],
                        separado[4],
                        separado[5],
                        separado[6],
                        separado[7],
                        separado[8],
                        separado[9],
                        separado[10],
                        LocalDate.parse(separado[12], formato),
                        Integer.parseInt(separado[13]),
                        separado[14],
                        separado[15],
                        separado[16],
                        separado[17]);
                todosPersonagens.add(aSerAdicionado);
            }
        }
        return todosPersonagens;
    }

    public void imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataNascimentoFormatada = dateOfBirth.format(formatter);

        String nomesAlternaticos = alternate_names.toString();
        nomesAlternaticos = nomesAlternaticos.replace("[", "{").replace("]", "}").replaceAll("\\s+", " ");

        System.out.println("[" + id + " ## " + name + " ## " + nomesAlternaticos + " ## " + house +
                " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
                hogwartsStaff + " ## " + hogwartsStudent + " ## " + actorName +
                " ## " + alive + " ## " + dataNascimentoFormatada + " ## " + yearOfBirth +
                " ## " + eyeColour + " ## " + gender + " ## " + hairColour + " ## " + Wizard + "]");

    }

}

public class Questao {

    public static void swap(List<Personagem> personagens, int indice1, int indice2) {
        Personagem temp = personagens.get(indice1);
        personagens.set(indice1, personagens.get(indice2));
        personagens.set(indice2, temp);
    }

    public static void salvarLog(int comparacoes, int movimentacoes, long tempoExecucao) {
        String nomeArquivo = "803627_quickSortParcial.txt";
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(803627 + "\t" + "Comparacoes" + comparacoes + "\t" + "Movimentacoes" + movimentacoes + "\t"
                    + "Tempo(nanoSec)" + (tempoExecucao));
        } catch (IOException e) {
            System.out.println("Erro ao salvar o log: " + e.getMessage());
        }
    }

    public static Personagem procurarPersonagemId(List<Personagem> todospersonagems, String id) {
        for (Personagem personagem : todospersonagems) {
            if (personagem.getId().equals(id)) {
                return personagem.clonePersonagem();
            }
        }
        return null;
    }

    public static Personagem procurarPersonagemName(List<Personagem> todospersonagems, String name) {
        for (Personagem personagem : todospersonagems) {
            if (personagem.getName().equals(name)) {
                return personagem.clonePersonagem();
            }
        }
        return null;
    }

    public static void selecao(List<Personagem> desarrumados, int contadores[]) {
        int n = desarrumados.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                contadores[0]++;
                int comparacao = desarrumados.get(i).getName().compareTo(desarrumados.get(j).getName());
                if (comparacao > 0) {
                    swap(desarrumados, i, j);
                    contadores[1] += 2;
                }
            }
        }
    }

    public static void insercao(List<Personagem> desarrumados, int contadores[]) {
        int n = desarrumados.size();
        for (int i = 1; i < n; i++) {
            Personagem personagemAtual = desarrumados.get(i);
            int j = i - 1;
            while (j >= 0) {
                contadores[0]++;

                if (desarrumados.get(j).getDateOfBirth().isAfter(personagemAtual.getDateOfBirth())) {
                    desarrumados.set(j + 1, desarrumados.get(j));
                    contadores[1]++;
                    j--;
                } else if (desarrumados.get(j).getDateOfBirth().isEqual(personagemAtual.getDateOfBirth()) &&
                        desarrumados.get(j).getName().compareTo(personagemAtual.getName()) > 0) {
                    desarrumados.set(j + 1, desarrumados.get(j));
                    contadores[1]++;
                    j--;
                } else {
                    break;
                }
            }
            desarrumados.set(j + 1, personagemAtual);
            contadores[1]++;
        }
    }

    public static void heapSort(List<Personagem> personagens, int[] contadores) {
        int n = personagens.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            rearranjar(personagens, n, i, contadores);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(personagens, 0, i);
            contadores[1]++;
            rearranjar(personagens, i, 0, contadores);
        }
    }

    private static void rearranjar(List<Personagem> personagens, int n, int i, int[] contadores) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n) {
            contadores[0]++;
            int comparacaoEsq = personagens.get(esq).getHairColour().compareTo(personagens.get(maior).getHairColour());
            if (comparacaoEsq > 0) {
                maior = esq;
            } else if (comparacaoEsq == 0) {
                if (personagens.get(esq).getName().compareTo(personagens.get(maior).getName()) > 0) {
                    maior = esq;
                }
            }
        }
        if (dir < n) {
            contadores[0]++;
            int comparacaoDir = personagens.get(dir).getHairColour().compareTo(personagens.get(maior).getHairColour());
            if (comparacaoDir > 0) {
                maior = dir;
            } else if (comparacaoDir == 0) {
                if (personagens.get(dir).getName().compareTo(personagens.get(maior).getName()) > 0) {
                    maior = dir;
                }
            }
        }
        if (maior != i) {
            swap(personagens, i, maior);
            contadores[1]++;
            rearranjar(personagens, n, maior, contadores);
        }
    }

    public static void countingSort(List<Personagem> personagens, int[] contadores) {
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;

        for (Personagem personagem : personagens) {
            int yearOfBirth = personagem.getYearOfBirth();
            if (yearOfBirth < minYear) {
                minYear = yearOfBirth;
            }
            if (yearOfBirth > maxYear) {
                maxYear = yearOfBirth;
            }
        }

        int[] count = new int[maxYear - minYear + 1];

        for (Personagem personagem : personagens) {
            int yearOfBirth = personagem.getYearOfBirth();
            count[yearOfBirth - minYear]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        List<Personagem> sorted = new ArrayList<>(personagens.size());
        for (int i = 0; i < personagens.size(); i++) {
            sorted.add(null);
        }

        for (int i = personagens.size() - 1; i >= 0; i--) {
            Personagem personagem = personagens.get(i);
            int yearOfBirth = personagem.getYearOfBirth();
            int posicao = count[yearOfBirth - minYear] - 1;
            sorted.set(posicao, personagem);
            count[yearOfBirth - minYear]--;
            contadores[1]++;
        }
        for (int i = 0; i <= maxYear - minYear; i++) {
            int start = (i == 0) ? 0 : count[i - 1];
            int end = count[i];
            sorted.subList(start, end).sort((p1, p2) -> {
                contadores[0]++;
                return p1.getName().compareTo(p2.getName());
            });
        }
        for (int i = 0; i < personagens.size(); i++) {
            personagens.set(i, sorted.get(i));
        }
    }

    public static void mergeSort(List<Personagem> personagens, int[] contadores, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(personagens, contadores, esq, meio);
            mergeSort(personagens, contadores, meio + 1, dir);
            merge(personagens, contadores, esq, meio, dir);
        }
    }

    private static void merge(List<Personagem> personagens, int[] contadores, int esq, int meio, int dir) {

        int tamanhoEsq = meio - esq + 1;
        int tamanhoDir = dir - meio;

        Personagem[] subListaEsq = new Personagem[tamanhoEsq];
        Personagem[] subListaDir = new Personagem[tamanhoDir];

        for (int i = 0; i < tamanhoEsq; i++) {
            subListaEsq[i] = personagens.get(esq + i);
        }

        for (int j = 0; j < tamanhoDir; j++) {
            subListaDir[j] = personagens.get(meio + 1 + j);
        }
        int i = 0, j = 0, k = esq;

        while (i < tamanhoEsq && j < tamanhoDir) {
            int comparacao = subListaEsq[i].getActorName().compareTo(subListaDir[j].getActorName());
            contadores[0]++;

            if (comparacao == 0) {
                comparacao = subListaEsq[i].getName().compareTo(subListaDir[j].getName());
                contadores[0]++;
            }

            if (comparacao <= 0) {
                personagens.set(k, subListaEsq[i]);
                i++;
            } else {
                personagens.set(k, subListaDir[j]);
                j++;
            }

            contadores[1]++;
            k++;
        }

        while (i < tamanhoEsq) {
            personagens.set(k, subListaEsq[i]);
            i++;
            k++;
            contadores[1]++;
        }
        while (j < tamanhoDir) {
            personagens.set(k, subListaDir[j]);
            j++;
            k++;
            contadores[1]++;
        }
    }

    public static void selecaoParcial(List<Personagem> personagens, int[] contadores, int k) {
        int n = personagens.size();
        if (k > n) {
            k = n;
        }
        for (int i = 0; i < k; i++) {
            int menorIndex = i;
            for (int j = i + 1; j < n; j++) {
                contadores[0]++;
                LocalDate dataMenor = personagens.get(menorIndex).getDateOfBirth();
                LocalDate dataAtual = personagens.get(j).getDateOfBirth();
                int comparacao = dataMenor.compareTo(dataAtual);
                if (comparacao == 0) {
                    comparacao = personagens.get(menorIndex).getName().compareTo(personagens.get(j).getName());
                    contadores[0]++;
                }
                if (comparacao > 0) {
                    menorIndex = j;
                }
            }

            if (menorIndex != i) {
                swap(personagens, i, menorIndex);
                contadores[1]++;
            }
        }
    }

    public static void quickSort(List<Personagem> personagens, int[] contadores, int esq, int dir, int k) {
        int i = esq, j = dir;
        Personagem pivo = personagens.get((dir + esq) / 2);
        while (i <= j) {
            while (personagens.get(i).getHouse().compareTo(pivo.getHouse()) < 0 ||
                    (personagens.get(i).getHouse().compareTo(pivo.getHouse()) == 0
                            && personagens.get(i).getName().compareTo(pivo.getName()) < 0)) {
                contadores[0]++;
                i++;
            }
            while (personagens.get(j).getHouse().compareTo(pivo.getHouse()) > 0 ||
                    (personagens.get(j).getHouse().compareTo(pivo.getHouse()) == 0
                            && personagens.get(j).getName().compareTo(pivo.getName()) > 0)) {
                contadores[0]++;
                j--;
            }
            if (i <= j) {
                swap(personagens, i, j);
                contadores[1]++;
                i++;
                j--;
            }
        }
        if (esq < j)
            quickSort(personagens, contadores, esq, j, k);
        if (i < k && i < dir)
            quickSort(personagens, contadores, i, dir, k);
    }

    // public static void Q1(List<Personagem> todoPersonagems) {
    // Scanner sc = new Scanner(System.in);
    // String id;
    // while (!(id = sc.nextLine()).equals("FIM")) {
    // for (int i = 0; i < todoPersonagems.size(); i++) {
    // if (todoPersonagems.get(i).getId().equals(id)) {
    // todoPersonagems.get(i).imprimir();
    // i = todoPersonagems.size();
    // }
    // }
    // }
    // sc.close();
    // }

    // public static void Q3(List<Personagem> todoPersonagems){
    // List<Personagem> selecionados = new ArrayList<>();
    // Scanner sc = new Scanner(System.in);
    // String id;
    // while(!(id = sc.nextLine()).equals("FIM")){
    // Personagem procurado = procurarPersonagemId(todoPersonagems, id);
    // if(procurado != null){
    // selecionados.add(procurarPersonagemId(todoPersonagems, id));
    // }
    // }
    // while(!(id = sc.nextLine()).equals("FIM")){
    // Personagem procurado = procurarPersonagemName(selecionados, id);
    // if(procurado != null){
    // System.out.println("SIM");
    // }
    // else{
    // System.out.println("NAO");
    // }
    // }
    // sc.close();
    // }

    // public static void Q5(List<Personagem> todosPersonagens) {
    // List<Personagem> selecionados = new ArrayList<>();
    // Scanner sc = new Scanner(System.in);
    // String id;
    // while (!(id = sc.nextLine()).equals("FIM")) {
    // Personagem procurado = procurarPersonagemId(todosPersonagens, id);
    // if (procurado != null) {
    // selecionados.add(procurarPersonagemId(todosPersonagens, id));
    // }
    // }
    // int[] contadores = new int[] { 0, 0 };
    // long inicio = System.nanoTime();
    // selecao(selecionados, contadores);
    // long fim = System.nanoTime();
    // long tempoExecucao = fim - inicio;
    // salvarLog(contadores[0], contadores[1], tempoExecucao);
    // for(Personagem personagem : selecionados){
    // personagem.imprimir();
    // }
    // sc.close();
    // }

    // public static void Q7(List<Personagem> todosPersonagens) {

    // List<Personagem> selecionados = new ArrayList<>();
    // Scanner sc = new Scanner(System.in);
    // String id;
    // while (!(id = sc.nextLine()).equals("FIM")) {
    // Personagem procurado = procurarPersonagemId(todosPersonagens, id);
    // if (procurado != null) {
    // selecionados.add(procurarPersonagemId(todosPersonagens, id));
    // }
    // }
    // int[] contadores = new int[] { 0, 0 };
    // long inicio = System.nanoTime();
    // insercao(selecionados, contadores);
    // long fim = System.nanoTime();
    // long tempoExecucao = fim - inicio;
    // salvarLog(contadores[0], contadores[1], tempoExecucao);
    // for (Personagem personagem : selecionados) {
    // personagem.imprimir();
    // }
    // sc.close();
    // }

    public static void Q9(List<Personagem> todosPersonagens) {

        List<Personagem> selecionados = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String id;
        while (!(id = sc.nextLine()).equals("FIM")) {
            Personagem procurado = procurarPersonagemId(todosPersonagens, id);
            if (procurado != null) {
                selecionados.add(procurarPersonagemId(todosPersonagens, id));
            }
        }
        int[] contadores = new int[] { 0, 0 };
        long inicio = System.nanoTime();
        insercao(selecionados, contadores);
        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;
        salvarLog(contadores[0], contadores[1], tempoExecucao);
        for (Personagem personagem : selecionados) {
            personagem.imprimir();
        }
        sc.close();
    }

    public static void Q11(List<Personagem> todosPersonagens) {

        List<Personagem> selecionados = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String id;
        while (!(id = sc.nextLine()).equals("FIM")) {
            Personagem procurado = procurarPersonagemId(todosPersonagens, id);
            if (procurado != null) {
                selecionados.add(procurarPersonagemId(todosPersonagens, id));
            }
        }
        int[] contadores = new int[] { 0, 0 };
        long inicio = System.nanoTime();
        countingSort(selecionados, contadores);
        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;
        salvarLog(contadores[0], contadores[1], tempoExecucao);
        for (Personagem personagem : selecionados) {
            personagem.imprimir();
        }
        sc.close();
    }

    public static void Q13(List<Personagem> todosPersonagens) {

        List<Personagem> selecionados = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String id;
        while (!(id = sc.nextLine()).equals("FIM")) {
            Personagem procurado = procurarPersonagemId(todosPersonagens, id);
            if (procurado != null) {
                selecionados.add(procurarPersonagemId(todosPersonagens, id));
            }
        }
        int[] contadores = new int[] { 0, 0 };
        long inicio = System.nanoTime();
        mergeSort(selecionados, contadores, 0, selecionados.size() - 1);
        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;
        salvarLog(contadores[0], contadores[1], tempoExecucao);
        for (Personagem personagem : selecionados) {
            personagem.imprimir();
        }
        sc.close();
    }

    public static void Q1516(List<Personagem> todosPersonagens){
    List<Personagem> selecionados = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String id;
    while (!(id = sc.nextLine()).equals("FIM")) {
    Personagem procurado = procurarPersonagemId(todosPersonagens, id);
    if (procurado != null) {
    selecionados.add(procurarPersonagemId(todosPersonagens, id));
    }
    }
    int[] contadores = new int[] { 0, 0 };
    long inicio = System.nanoTime();
    selecaoParcial(selecionados, contadores, 10);
    long fim = System.nanoTime();
    long tempoExecucao = fim - inicio;
    salvarLog(contadores[0], contadores[1], tempoExecucao);
    for(int i = 0 ; i < 10 ; i++){
    selecionados.get(i).imprimir();
    }
    sc.close();
    }
    public static void Q18(List<Personagem> todosPersonagens) {

        List<Personagem> selecionados = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String id;
        while (!(id = sc.nextLine()).equals("FIM")) {
            Personagem procurado = procurarPersonagemId(todosPersonagens, id);
            if (procurado != null) {
                selecionados.add(procurarPersonagemId(todosPersonagens, id));
            }
        }
        int[] contadores = new int[] { 0, 0 };
        long inicio = System.nanoTime();
        quickSort(selecionados, contadores, 0, (selecionados.size() - 1), 10);
        long fim = System.nanoTime();
        long tempoExecucao = fim - inicio;
        salvarLog(contadores[0], contadores[1], tempoExecucao);
        for (int i = 0; i < 10; i++) {
            selecionados.get(i).imprimir();
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        String path = "/tmp/characters.csv";
        Personagem chamarFuncao = new Personagem();
        List<Personagem> todoPersonagems = new ArrayList<>();
        todoPersonagems = chamarFuncao.ler(path);

        /* chamada da questao */
        Q18(todoPersonagems);

    }
}