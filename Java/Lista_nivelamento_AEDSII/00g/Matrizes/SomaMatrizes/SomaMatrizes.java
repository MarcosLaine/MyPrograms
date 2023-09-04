import java.util.Scanner;

public class SomaMatrizes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de linhas: ");
        int linhas = scanner.nextInt();

        System.out.print("Digite o número de colunas: ");
        int colunas = scanner.nextInt();

        int[][] matriz1 = new int[linhas][colunas];
        int[][] matriz2 = new int[linhas][colunas];

        System.out.println("Digite os elementos da primeira matriz:");
        lerMatriz(matriz1, linhas, colunas, scanner);

        System.out.println("Digite os elementos da segunda matriz:");
        lerMatriz(matriz2, linhas, colunas, scanner);

        int[][] matrizSoma = somarMatrizes(matriz1, matriz2, linhas, colunas);

        System.out.println("Matriz resultante da soma:");
        imprimirMatriz(matrizSoma, linhas, colunas);

        scanner.close();
    }

    public static void lerMatriz(int[][] matriz, int linhas, int colunas, Scanner scanner) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }
    }

    public static int[][] somarMatrizes(int[][] matriz1, int[][] matriz2, int linhas, int colunas) {
        int[][] matrizSoma = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matrizSoma[i][j] = matriz1[i][j] + matriz2[i][j];
            }
        }
        return matrizSoma;
    }

    public static void imprimirMatriz(int[][] matriz, int linhas, int colunas) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
