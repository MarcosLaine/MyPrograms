import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arq {
    private static BufferedReader reader;

    // Método para abrir um arquivo para leitura
    public static void openRead(String filename) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    // Método para ler uma linha do arquivo
    public static String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Erro ao ler a linha do arquivo: " + e.getMessage());
            return null;
        }
    }

    // Método para fechar o arquivo
    public static void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
        }
    }
}
