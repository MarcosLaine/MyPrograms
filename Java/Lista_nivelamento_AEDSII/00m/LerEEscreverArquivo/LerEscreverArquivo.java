import java.io.*;
import java.util.Scanner;

public class LerEscreverArquivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de números: ");
        int n = scanner.nextInt();
        
        try {
            // Escrevendo os números no arquivo
            FileWriter fileWriter = new FileWriter("numeros.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            System.out.println("Digite os números:");
            for (int i = 0; i < n; i++) {
                int num = scanner.nextInt();
                printWriter.println(num);
            }

            printWriter.close();
            fileWriter.close();

            // Lendo e mostrando os números do arquivo
            FileReader fileReader = new FileReader("numeros.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println("\nNúmeros lidos do arquivo:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int num = Integer.parseInt(line);
                System.out.println(num);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Erro ao manipular o arquivo: " + e.getMessage());
        }
        
        scanner.close();
    }
}
