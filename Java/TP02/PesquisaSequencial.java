import java.io.*;
import java.util.*;

public class PesquisaSequencial {
    public static void main(String[] args) throws IOException {
        // Crie um vetor para armazenar os registros.
        ArrayList<Registro> vetor = new ArrayList<>();

        // Leia a entrada padrão e insira os registros no vetor.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = br.readLine()).equals("FIM")) {
            Registro novoRegistro = new Registro(input);
            vetor.add(novoRegistro);
        }

        // Leia os elementos a serem pesquisados.
        ArrayList<String> elementosPesquisados = new ArrayList<>();
        while (!(input = br.readLine()).equals("FIM")) {
            elementosPesquisados.add(input);
        }

        // Realize a pesquisa sequencial.
        ArrayList<String> resultados = new ArrayList<>();
        int comparacoes = 0;
        for (String elemento : elementosPesquisados) {
            boolean encontrado = false;
            for (Registro registro : vetor) {
                comparacoes++;
                if (registro.nome.equals(elemento)) {
                    encontrado = true;
                    break;
                }
            }
            resultados.add(encontrado ? "SIM" : "NAO");
        }

        // Escreva a saída padrão.
        for (String resultado : resultados) {
            System.out.println(resultado);
        }

        // Escreva o registro de log.
        String matricula = "803627"; // Substitua pelo seu número de matrícula.
        long tempoExecucao = System.currentTimeMillis();
        try (PrintWriter logFile = new PrintWriter("matricula_sequencial.txt")) {
            logFile.println(matricula + "\t" + tempoExecucao + "\t" + comparacoes);
        }
    }
}

class Registro {
    String nome;

    public Registro(String nome) {
        this.nome = nome;
    }

    // Você pode adicionar outros atributos e métodos, se necessário.
}

