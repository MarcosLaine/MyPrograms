import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Atleta {

    private int identificador;
    private String nomeAtleta;
    private int estatura;
    private int massa;
    private String instituicao;
    private int anoNasc;
    private String cidadeOrigem;
    private String estadoOrigem;

    public Atleta() {
        this.identificador = -1;
        this.nomeAtleta = "";
        this.estatura = -1;
        this.massa = -1;
        this.instituicao = "";
        this.anoNasc = -1;
        this.cidadeOrigem = "";
        this.estadoOrigem = "";
    }

    public Atleta(
        int id,
        String nome,
        int altura,
        int peso,
        String universidade,
        int anoNascimento,
        String cidadeNascimento,
        String estadoNascimento
    ) {
        this.identificador = id;
        this.nomeAtleta = nome;
        this.estatura = altura;
        this.massa = peso;
        this.instituicao = universidade;
        this.anoNasc = anoNascimento;
        this.cidadeOrigem = cidadeNascimento;
        this.estadoOrigem = estadoNascimento;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public int getEstatura() {
        return estatura;
    }

    public int getMassa() {
        return massa;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public void setMassa(int massa) {
        this.massa = massa;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public Atleta clone() throws CloneNotSupportedException {
        Atleta clone = new Atleta();
        clone.identificador = this.identificador;
        clone.nomeAtleta = this.nomeAtleta;
        clone.estatura = this.estatura;
        clone.massa = this.massa;
        clone.instituicao = this.instituicao;
        clone.anoNasc = this.anoNasc;
        clone.cidadeOrigem = this.cidadeOrigem;
        clone.estadoOrigem = this.estadoOrigem;
        return clone;
    }

    public void interpretarDados(String data) {
    if (data != null) {
        String[] infos = data.split(",", -1); // O parâmetro -1 garante que strings vazias sejam incluídas no array.

        // Verifica se todos os elementos necessários estão presentes.
        if (infos.length >= 8) {
            this.setIdentificador(infos[0].isEmpty() ? -1 : Integer.parseInt(infos[0]));
            this.setNomeAtleta(infos[1]);
            this.setEstatura(infos[2].isEmpty() ? 0 : Integer.parseInt(infos[2]));
            this.setMassa(infos[3].isEmpty() ? 0 : Integer.parseInt(infos[3]));
            this.setInstituicao(infos[4]);
            this.setAnoNasc(infos[5].isEmpty() ? 0 : Integer.parseInt(infos[5]));
            this.setCidadeOrigem(infos[6]);
            this.setEstadoOrigem(infos[7]);
        } else {
            System.out.println("Dados incompletos para o atleta: " + data);
        }
    }
}


    public void exibir() {
        System.out.println(
            identificador +
            " ## " +
            nomeAtleta +
            " ## " +
            estatura +
            " ## " +
            massa +
            " ## " +
            anoNasc +
            " ## " +
            instituicao +
            " ## " +
            cidadeOrigem +
            " ## " +
            estadoOrigem +
            " ##"
        );
    }
}

public class TabelaHash {
    Atleta[] tabelaHash = new Atleta[21];
    Atleta[] areaReserva = new Atleta[9];

    TabelaHash() {
        inicia();
    }

    void inicia() {
        for (int i = 0; i < 21; i++) {
            tabelaHash[i] = null;
        }
        for (int i = 0; i < 9; i++) {
            areaReserva[i] = null;
        }
    }

    int calcularPosicao(Atleta atleta) {
        return atleta.getEstatura() % 21;
    }

    void adicionar(Atleta x) {
        int pos = calcularPosicao(x);
        if (tabelaHash[pos] == null) {
            tabelaHash[pos] = x;
        } else {
            int i = 0;
            while (i < 9 && areaReserva[i] != null) {
                i++;
            }
            if (i < 9) {
                areaReserva[i] = x;
            }
        }
    }

    void exibirTabela() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tabelaHash.length; i++) {
            if (tabelaHash[i] != null) {
                tabelaHash[i].exibir();
            }
        }

        System.out.println("\nÁrea de Reserva:");
        for (int i = 0; i < areaReserva.length; i++) {
            if (areaReserva[i] != null) {
                areaReserva[i].exibir();
            }
        }
    }

    boolean procurarAtleta(String nome) {
        for (int i = 0; i < tabelaHash.length; i++) {
            if (tabelaHash[i] != null && tabelaHash[i].getNomeAtleta().equals(nome)) {
                return true;
            }
        }
        for (int i = 0; i < areaReserva.length; i++) {
            if (areaReserva[i] != null && areaReserva[i].getNomeAtleta().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public static String[] lerArquivo(String caminho) {
        File file = new File(caminho);
        String[] arrData = new String[3922];
        int i = 0;
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {
                arrData[i] = scanner.nextLine();
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
        return arrData;
    }
    
    public static void main(String[] args) throws Exception {
        String[] arrData = lerArquivo("/tmp/players.csv");
        Atleta[] jogadorData = new Atleta[arrData.length];
        Scanner teclado = new Scanner(System.in);
        TabelaHash hash = new TabelaHash();

        for (int i = 0; i < arrData.length; i++) {
            String data = arrData[i];
            Atleta atleta = new Atleta();
            atleta.interpretarDados(data);
            jogadorData[i] = atleta;
        }

        while (!teclado.hasNext("FIM")) {
            int id = teclado.nextInt();
            hash.adicionar(jogadorData[id]);
        }

        teclado.nextLine();
        teclado.nextLine();

        while (!teclado.hasNext("FIM")) {
            String nome = teclado.nextLine();
            System.out.print(nome + " ");
            
            if (hash.procurarAtleta(nome)) System.out.print("SIM\n");
            else System.out.print("NAO\n");
        }

        teclado.close();
    }
}
