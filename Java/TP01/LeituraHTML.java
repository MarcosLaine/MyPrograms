import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LeituraHTML{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> caracteres = new HashMap<>();
        String entradas = "aeiouáéíóúàèìòùãõâêîôû";

        // Inicialize os contadores para cada caractere ou string solicitado.
        for (char c : entradas.toCharArray()) {
            caracteres.put(c, 0);
        }

        int consoantes = 0;
        int brCount = 0;
        int tableCount = 0;
        int nomePaginaCount = 0;

        String linha;
        boolean coletandoDados = false;

        while ((linha = br.readLine()) != null) {
            if (linha.equals("FIM")) {
                break;
            }

            if (coletandoDados) {
                for (int i = 0; i < linha.length(); i++) {
                    char c = Character.toLowerCase(linha.charAt(i));

                    // Verifique se o caractere é uma vogal (com ou sem acento).
                    if (entradas.indexOf(c) != -1) {
                        caracteres.put(c, caracteres.get(c) + 1);
                    } else if (c != ' ') {
                        // Se não for uma vogal nem um espaço em branco, é uma consoante.
                        consoantes++;
                    }
                }

                // Verifique se a linha contém as strings <br> e <table>.
                if (linha.contains("<br>")) {
                    brCount++;
                }
                if (linha.contains("<table>")) {
                    tableCount++;
                }
            } else {
                // Se não estivermos coletando dados, lemos o endereço da página e o nome da página.
                String endereco = linha;
                String nomePagina = br.readLine();
                coletandoDados = true;

                System.out.print(endereco + "\n" + nomePagina + "\n");
            }
        }

        // Imprima os resultados formatados de acordo com as especificações sem quebras de linha.
        for (char c : entradas.toCharArray()) {
            System.out.print(c + "(" + caracteres.get(c) + ")");
        }

        System.out.print("consoante(" + consoantes + ")");
        System.out.print("<br>(" + brCount + ")");
        System.out.print("<table>(" + tableCount + ")");
        System.out.print("nomepágina(" + nomePaginaCount + ")");
    }
}
