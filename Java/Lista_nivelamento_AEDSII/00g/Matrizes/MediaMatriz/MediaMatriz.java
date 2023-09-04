import java.util.Scanner;

public class MediaMatriz {
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

        double somaElementos = 0;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                somaElementos += matriz[i][j];
            }
        }

        double media = somaElementos / (linhas * colunas);

        System.out.println("A média dos elementos da matriz é: " + media);

        scanner.close();
    }
}
