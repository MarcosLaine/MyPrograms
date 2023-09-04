import java.util.Scanner;

public class MediaColunasMatriz {
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

        System.out.println("Média dos elementos de cada coluna:");
        for (int j = 0; j < colunas; j++) {
            double somaColuna = 0;
            for (int i = 0; i < linhas; i++) {
                somaColuna += matriz[i][j];
            }
            double mediaColuna = somaColuna / linhas;
            System.out.println("Média da coluna " + (j + 1) + ": " + mediaColuna);
        }

        scanner.close();
    }
}
