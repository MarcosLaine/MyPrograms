import java.util.Scanner;

public class MediaLinhasMatriz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de linhas: ");
        int linhas = scanner.nextInt();

        System.out.print("Digite o número de colunas: ");
        int colunas = scanner.nextInt();

        int[][] matriz = new int[linhas][colunas];

        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Média dos elementos de cada linha:");
        for (int i = 0; i < linhas; i++) {
            double somaLinha = 0;
            for (int j = 0; j < colunas; j++) {
                somaLinha += matriz[i][j];
            }
            double mediaLinha = somaLinha / colunas;
            System.out.println("Média da linha " + (i + 1) + ": " + mediaLinha);
        }

        scanner.close();
    }
}
