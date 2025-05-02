import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class abreEcopia {
    public static void main(String[] args) {
        String arquivoOrigem = "arquivo_origem.txt";
        String arquivoDestino = "arquivo_destino.txt";

        try {
            File arquivoOrigemFile = new File(arquivoOrigem);
            
            if (!arquivoOrigemFile.exists()) {
                criarArquivo(arquivoOrigemFile);
            }

            FileReader leitor = new FileReader(arquivoOrigemFile);
            FileWriter escritor = new FileWriter(arquivoDestino);

            int caractere;
            while ((caractere = leitor.read()) != -1) {
                escritor.write(caractere);
            }

            leitor.close();
            escritor.close();
            System.out.println("Cópia do arquivo concluída com sucesso.");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro durante a cópia do arquivo.");
            e.printStackTrace();
        }
    }

    public static void criarArquivo(File arquivo) throws IOException {
        if (!arquivo.createNewFile()) {
            throw new IOException("Não foi possível criar o arquivo.");
        }
    }
}
