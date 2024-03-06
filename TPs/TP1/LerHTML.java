import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LerHTML {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String entradas = "aeiouáéíóúàèìòùãõâêîôû";
        int[] contadorVogais = new int[entradas.length()]; // Contador para cada caractere na string de entradas

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
                    int index = entradas.indexOf(c);

                    // Verifique se o caractere é uma vogal (com ou sem acento).
                    if (index != -1) {
                        contadorVogais[index]++;
                    } else if (!Character.isWhitespace(c) && Character.isLetter(c)) {
                        // Se não for uma vogal nem um espaço em branco, e for uma letra, é uma consoante.
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

        for (int i = 0; i < entradas.length(); i++) {
            System.out.print(entradas.charAt(i) + "(" + contadorVogais[i] + ")");
        }

        System.out.print("consoante(" + consoantes + ")");
        System.out.print("<br>(" + brCount + ")");
        System.out.print("<table>(" + tableCount + ")");
        System.out.print("nomepágina(" + nomePaginaCount + ")");
    }
}
