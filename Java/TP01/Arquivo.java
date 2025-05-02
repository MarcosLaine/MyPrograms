import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Arquivo {
    public static void main(String[] args) {
        try {
            // Leitura do número inteiro n
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // Abertura do arquivo para escrita
            RandomAccessFile file = new RandomAccessFile("valores.txt", "rw");

            // Leitura dos valores e escrita no arquivo
            for (int i = 0; i < n; i++) {
                String linha = br.readLine();
                try {
                    double valor = Double.parseDouble(linha);
                    file.writeDouble(valor);
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido: " + linha + ". Será ignorado.");
                }
            }

            // Fechar o arquivo
            file.close();

            // Reabrir o arquivo para leitura reversa
            file = new RandomAccessFile("valores.txt", "r");

            // Posicionar o cursor no final do arquivo
            file.seek(file.length());

            // Ler os valores em ordem reversa e mostrar na tela
            while (file.getFilePointer() > 0) {
                file.seek(file.getFilePointer() - 8); // Tamanho de um double é 8 bytes
                double valor = file.readDouble();
                if (valor == (long) valor) {
                    System.out.println((long) valor); // Mostrar números inteiros sem decimais
                } else {
                    System.out.println(valor);
                }
                file.seek(file.getFilePointer() - 8); // Voltar para o próximo valor
            }

            // Fechar o arquivo novamente
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
