import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0; 
        altura = 0;
        peso = 0;
        anoNascimento = 0;
        nome = "";
        universidade = "";
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

   public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }
    
    public String getUniversidade() {
        return universidade;
    }

     public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void imprimirDados() {
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " 
        + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }

    public Jogador clone() {
        Jogador clone = new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());
        return clone;
    }

    public void lerDados(String dadosJogador) {
        String[] posicao = new String[8];
        int tmp = 0;
        int j = 0;
        for (int i = 0; i < dadosJogador.length(); i++) {
            if (dadosJogador.charAt(i) == ',') {
                posicao[j] = dadosJogador.substring(tmp, i);
                tmp = i + 1;
                if (posicao[j].intern() == "") {
                    posicao[j] = "nao informado";
                }
                j++;
            }
        }
        posicao[7] = dadosJogador.substring(tmp, dadosJogador.length());

        if (posicao[7] == "") {
            posicao[7] = "nao informado";
        }
        setId(Integer.parseInt(posicao[0]));
        setNome(posicao[1]);
        setAltura(Integer.parseInt(posicao[2]));
        setPeso(Integer.parseInt(posicao[3]));
        setUniversidade(posicao[4]);
        setAnoNascimento(Integer.parseInt(posicao[5]));
        setCidadeNascimento(posicao[6]);
        setEstadoNascimento(posicao[7]);
    }
}

public class TP02Q05 {
    public static void main(String[] args) {

        long tempoIni = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<>();
        
        try{
            FileReader fileReader = new FileReader("/tmp/players.csv");
            BufferedReader arquivo = new BufferedReader(fileReader);   
            arquivo.readLine();
            while (arquivo.ready()) {
                Jogador jogador = new Jogador();
                jogador.lerDados(arquivo.readLine());
                jogadores.add(jogador);
                }
                arquivo.close();
            }catch(Exception e){
               System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
            }
        ArrayList<Jogador> JogadorSelecionado = new ArrayList<>();
        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            JogadorSelecionado.add(jogadores.get(Integer.parseInt(id)));
            id = scanner.nextLine();
        }

        int[] contador = selectionSort(JogadorSelecionado);

        for (int i = 0; i < JogadorSelecionado.size(); i++) {
            JogadorSelecionado.get(i).imprimirDados();
        }

        long tempoFim = System.currentTimeMillis();
        try{
            FileWriter fileWriter = new FileWriter("matricula_selecao.txt");
            BufferedWriter arqWriter = new BufferedWriter(fileWriter);
            arqWriter.write("Matricula: 808664\tTempo: " +(tempoFim - tempoIni) / 1000d + "\tComparacoes: "+ contador[0] + "\tMovimentações: " + contador[1]);
            arqWriter.close();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
        }
        scanner.close();
    }

    public static int[] selectionSort(ArrayList<Jogador> JogadorSelecionado) {
        int n = JogadorSelecionado.size();
        int comparacoes = 0;
        int movimentacoes = 0;
        for (int i = 0; i < n-1; i++) {
           int menor = i;
           for (int j = (i + 1); j < n; j++){
            comparacoes++;
            if (JogadorSelecionado.get(j).getNome().compareToIgnoreCase(JogadorSelecionado.get(menor).getNome()) < 0) {
                menor = j;
              }
           }
           swap(JogadorSelecionado,menor, i);
              movimentacoes +=3;
        }
        return new int[] {comparacoes,movimentacoes};
     }
     public static void swap(ArrayList<Jogador> JogadorSelecionado, int i, int j) {
        Jogador temp = JogadorSelecionado.get(i);
        JogadorSelecionado.set(i, JogadorSelecionado.get(j));
        JogadorSelecionado.set(j, temp);
    }
}