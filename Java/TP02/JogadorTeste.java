import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    private static List<Jogador> listaJogadores = new ArrayList<>();
    private static final String FILE_NAME = "/tmp/players.csv";//para acessar o arquivo com os jogadores
    //CONSTRUTORES
    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
        String cidadeNascimento, String estadoNascimento)
    { //inicializar atributos de jogador
      this.id = id;
      this.nome = nome;
      this.altura = altura;
      this.peso = peso;
      this.universidade = universidade;
      this.anoNascimento = anoNascimento;
      this.cidadeNascimento = cidadeNascimento;
      this.estadoNascimento = estadoNascimento;
    }
    public Jogador() {
        while (true) {
        System.out.println("Informe o ID do jogador (ou 'FIM' para encerrar):");
        String input = MyIO.readLine().trim();//evitar problemas de espaços indesejados
        //condição de parada quando recebe 'fim'
        if (input.equalsIgnoreCase("FIM")) 
        { break; }
        int jogadorId = Integer.parseInt(input);//converter de string para inteiro
        boolean jogadorEncontrado = false;

        for (Jogador jogador : listaJogadores) 
        {
            if (jogador.getId() == jogadorId)
             {
                jogadorEncontrado = true;
                jogador.imprimir();//chama método imprimir se o jogador existir
                break;
                }
            }
            if (!jogadorEncontrado) {
               // System.out.println("Jogador não encontrado");  
            }
        }
        System.exit(0);//encerra o programa após sair do loop
    }
    //Métos get e set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public String getUniversidade() {
        return universidade;
    }
    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
    public int getAnoNascimento() {
        return anoNascimento;
    }
    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    public String getCidadeNascimento() {
        return cidadeNascimento;
    }
    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }
    public String getEstadoNascimento() {
        return estadoNascimento;
    }
    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }
    //Método imprimir
    public void imprimir() {
        System.out.print("[" + id);
        System.out.print(" ## " + (nome != null ? nome : "não informado"));
        System.out.print(" ## " + altura);
        System.out.print(" ## " + peso);
        System.out.print(" ## " + (universidade != null ? universidade : "não informado"));
        System.out.print(" ## " + anoNascimento);
        System.out.print(" ## " + (cidadeNascimento != null ? cidadeNascimento : "não informado"));
        System.out.print(" ## " + (estadoNascimento != null ? estadoNascimento : "não informado"));
        System.out.println("]");
    }
    public static void lerDadosDoArquivo() {
        listaJogadores.clear();
        //ler dados do arquivo players.txt
        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String ln;
            while ((ln = ler.readLine()) != null) 
            {
              String[] atributo = ln.split(",");
              if (atributo.length >= 1) 
              {
                int id;
                try { //exceção
                    id = Integer.parseInt(atributo[0].trim()); //remove os espaços em branco
                } catch (NumberFormatException a) {
                    //Se não for um número válido muda para a próxima linha
                    continue;
                }//obter as informações do arquivo
                String nome = (atributo.length > 1) ? atributo[1].trim() : "nao informado";
                int altura = (atributo.length > 2) ? Integer.parseInt(atributo[2].trim()) : 0;
                int peso = (atributo.length > 3) ? Integer.parseInt(atributo[3].trim()) : 0;
                String universidade = (atributo.length > 4) ? atributo[4].trim() : "nao informado";
                int anoNascimento = (atributo.length > 5) ? Integer.parseInt(atributo[5].trim()) : 0;
                String cidadeNascimento = (atributo.length > 6) ? atributo[6].trim() : "nao informado";
                String estadoNascimento = (atributo.length > 7) ? atributo[7].trim() : "nao informado";
                //cria um objeto Jogador e adiciona na lista
                Jogador jogador = new Jogador(id, nome, altura, peso, universidade, anoNascimento, 
                cidadeNascimento,estadoNascimento);
                listaJogadores.add(jogador);
              }
            }
        } catch (IOException x) {//no caso de exceção 
            x.printStackTrace();//informações sobre o erro
      }
    }
    public static void main(String[] args) {
        //chama o método de leitura
        lerDadosDoArquivo();
        //chama construtor vazio
        while (true) 
        {
          new Jogador();
        }
    }
}
